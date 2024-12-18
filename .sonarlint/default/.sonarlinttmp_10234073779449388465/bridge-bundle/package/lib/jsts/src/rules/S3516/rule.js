"use strict";
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
// https://sonarsource.github.io/rspec/#/rspec/S3516/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, undefined, true),
    create(context) {
        const functionContextStack = [];
        const codePathSegments = [];
        let currentCodePathSegments = [];
        const checkOnFunctionExit = (node) => checkInvariantReturnStatements(node, functionContextStack[functionContextStack.length - 1]);
        function checkInvariantReturnStatements(node, functionContext) {
            if (!functionContext || hasDifferentReturnTypes(functionContext, currentCodePathSegments)) {
                return;
            }
            const returnedValues = functionContext.returnStatements.map(returnStatement => returnStatement.argument);
            if (areAllSameValue(returnedValues, context.sourceCode.getScope(node))) {
                (0, helpers_1.report)(context, {
                    message: `Refactor this function to not always return the same value.`,
                    loc: (0, helpers_1.getMainFunctionTokenLocation)(node, (0, helpers_1.getParent)(context, node), context),
                }, returnedValues.map(node => (0, helpers_1.toSecondaryLocation)(node, 'Returned value.')), returnedValues.length);
            }
        }
        return {
            onCodePathStart(codePath) {
                functionContextStack.push({
                    codePath,
                    containsReturnWithoutValue: false,
                    returnStatements: [],
                });
                codePathSegments.push(currentCodePathSegments);
                currentCodePathSegments = [];
            },
            onCodePathEnd() {
                functionContextStack.pop();
                currentCodePathSegments = codePathSegments.pop() || [];
            },
            onCodePathSegmentStart: (segment) => {
                currentCodePathSegments.push(segment);
            },
            onCodePathSegmentEnd() {
                currentCodePathSegments.pop();
            },
            ReturnStatement(node) {
                const currentContext = functionContextStack[functionContextStack.length - 1];
                if (currentContext) {
                    const returnStatement = node;
                    currentContext.containsReturnWithoutValue =
                        currentContext.containsReturnWithoutValue || !returnStatement.argument;
                    currentContext.returnStatements.push(returnStatement);
                }
            },
            'FunctionDeclaration:exit': checkOnFunctionExit,
            'FunctionExpression:exit': checkOnFunctionExit,
            'ArrowFunctionExpression:exit': checkOnFunctionExit,
        };
    },
};
function hasDifferentReturnTypes(functionContext, currentSegments) {
    // As this method is called at the exit point of a function definition, the current
    // segments are the ones leading to the exit point at the end of the function. If they
    // are reachable, it means there is an implicit return.
    const hasImplicitReturn = currentSegments.some(segment => segment.reachable);
    return (hasImplicitReturn ||
        functionContext.containsReturnWithoutValue ||
        functionContext.returnStatements.length <= 1 ||
        functionContext.codePath.thrownSegments.length > 0);
}
function areAllSameValue(returnedValues, scope) {
    const firstReturnedValue = returnedValues[0];
    const firstValue = getLiteralValue(firstReturnedValue, scope);
    if (firstValue !== undefined) {
        return returnedValues
            .slice(1)
            .every(returnedValue => getLiteralValue(returnedValue, scope) === firstValue);
    }
    else if (firstReturnedValue.type === 'Identifier') {
        const singleWriteVariable = getSingleWriteDefinition(firstReturnedValue.name, scope);
        if (singleWriteVariable) {
            const readReferenceIdentifiers = singleWriteVariable.variable.references
                .slice(1)
                .map(ref => ref.identifier);
            return returnedValues.every(returnedValue => readReferenceIdentifiers.includes(returnedValue));
        }
    }
    return false;
}
function getSingleWriteDefinition(variableName, scope) {
    const variable = scope.set.get(variableName);
    if (variable) {
        const references = variable.references.slice(1);
        if (!references.some(ref => ref.isWrite() || isPossibleObjectUpdate(ref))) {
            let initExpression = null;
            if (variable.defs.length === 1 && variable.defs[0].type === 'Variable') {
                initExpression = variable.defs[0].node.init;
            }
            return { variable, initExpression };
        }
    }
    return null;
}
function isPossibleObjectUpdate(ref) {
    const expressionStatement = (0, helpers_1.findFirstMatchingAncestor)(ref.identifier, n => n.type === 'ExpressionStatement' || helpers_1.FUNCTION_NODES.includes(n.type));
    // To avoid FP, we consider method calls as write operations, since we do not know whether they will
    // update the object state or not.
    return (expressionStatement &&
        expressionStatement.type === 'ExpressionStatement' &&
        ((0, helpers_1.isElementWrite)(expressionStatement, ref) ||
            expressionStatement.expression.type === 'CallExpression'));
}
function getLiteralValue(returnedValue, scope) {
    if (returnedValue.type === 'Literal') {
        return returnedValue.value;
    }
    else if (returnedValue.type === 'UnaryExpression') {
        const innerReturnedValue = getLiteralValue(returnedValue.argument, scope);
        return innerReturnedValue !== undefined
            ? evaluateUnaryLiteralExpression(returnedValue.operator, innerReturnedValue)
            : undefined;
    }
    else if (returnedValue.type === 'Identifier') {
        const singleWriteVariable = getSingleWriteDefinition(returnedValue.name, scope);
        if (singleWriteVariable?.initExpression) {
            return getLiteralValue(singleWriteVariable.initExpression, scope);
        }
    }
    return undefined;
}
function evaluateUnaryLiteralExpression(operator, innerReturnedValue) {
    switch (operator) {
        case '-':
            return -Number(innerReturnedValue);
        case '+':
            return Number(innerReturnedValue);
        case '~':
            return ~Number(innerReturnedValue);
        case '!':
            return !Boolean(innerReturnedValue);
        case 'typeof':
            return typeof innerReturnedValue;
        default:
            return undefined;
    }
}
//# sourceMappingURL=rule.js.map