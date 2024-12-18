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
// https://sonarsource.github.io/rspec/#/rspec/S4165/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        messages: {
            reviewAssignment: 'Review this redundant assignment: "{{symbol}}" already holds the assigned value along all execution paths.',
        },
    }),
    create(context) {
        const codePathStack = [];
        const reachingDefsMap = new Map();
        // map from Variable to CodePath ids where variable is used
        const variableUsages = new Map();
        const codePathSegments = [];
        let currentCodePathSegments = [];
        return {
            ':matches(AssignmentExpression, VariableDeclarator[init])': (node) => {
                pushAssignmentContext(node);
            },
            ':matches(AssignmentExpression, VariableDeclarator[init]):exit': () => {
                popAssignmentContext();
            },
            Identifier: (node) => {
                if (isEnumConstant(node)) {
                    return;
                }
                checkIdentifierUsage(node);
            },
            'Program:exit': () => {
                (0, helpers_1.reachingDefinitions)(reachingDefsMap);
                reachingDefsMap.forEach(defs => {
                    checkSegment(defs);
                });
                reachingDefsMap.clear();
                variableUsages.clear();
                while (codePathStack.length > 0) {
                    codePathStack.pop();
                }
            },
            // CodePath events
            onCodePathSegmentStart: (segment) => {
                reachingDefsMap.set(segment.id, new helpers_1.ReachingDefinitions(segment));
                currentCodePathSegments.push(segment);
            },
            onCodePathStart: codePath => {
                pushContext(new CodePathContext(codePath));
                codePathSegments.push(currentCodePathSegments);
                currentCodePathSegments = [];
            },
            onCodePathEnd: () => {
                popContext();
                currentCodePathSegments = codePathSegments.pop() || [];
            },
            onCodePathSegmentEnd() {
                currentCodePathSegments.pop();
            },
        };
        function popAssignmentContext() {
            const assignment = peek(codePathStack).assignmentStack.pop();
            assignment.rhs.forEach(r => processReference(r));
            assignment.lhs.forEach(r => processReference(r));
        }
        function pushAssignmentContext(node) {
            peek(codePathStack).assignmentStack.push(new AssignmentContext(node));
        }
        function checkSegment(reachingDefs) {
            const assignedValuesMap = new Map(reachingDefs.in);
            reachingDefs.references.forEach(ref => {
                const variable = ref.resolved;
                if (!variable || !ref.isWrite() || !shouldReport(ref)) {
                    return;
                }
                const lhsValues = assignedValuesMap.get(variable);
                const rhsValues = (0, helpers_1.resolveAssignedValues)(variable, ref.writeExpr, assignedValuesMap, ref.from);
                if (lhsValues?.type === 'AssignedValues' && lhsValues?.size === 1) {
                    const [lhsVal] = [...lhsValues];
                    checkRedundantAssignement(ref, ref.writeExpr, lhsVal, rhsValues, variable.name);
                }
                assignedValuesMap.set(variable, rhsValues);
            });
        }
        function checkRedundantAssignement({ resolved: variable }, node, lhsVal, rhsValues, name) {
            if (rhsValues.type === 'UnknownValue' || rhsValues.size !== 1) {
                return;
            }
            const [rhsVal] = [...rhsValues];
            if (!isWrittenOnlyOnce(variable) && lhsVal === rhsVal) {
                context.report({
                    node: node,
                    messageId: 'reviewAssignment',
                    data: {
                        symbol: name,
                    },
                });
            }
        }
        // to avoid raising on code like:
        // while (cond) {  let x = 42; }
        function isWrittenOnlyOnce(variable) {
            return variable.references.filter(ref => ref.isWrite()).length === 1;
        }
        function shouldReport(ref) {
            const variable = ref.resolved;
            return variable && shouldReportReference(ref) && !variableUsedOutsideOfCodePath(variable);
        }
        function shouldReportReference(ref) {
            const variable = ref.resolved;
            return (variable &&
                !isDefaultParameter(ref) &&
                !variable.name.startsWith('_') &&
                !isCompoundAssignment(ref.writeExpr) &&
                !isSelfAssignement(ref) &&
                !variable.defs.some(def => def.type === 'Parameter' || (def.type === 'Variable' && !def.node.init)));
        }
        function isEnumConstant(node) {
            return context.sourceCode.getAncestors(node).some(n => n.type === 'TSEnumDeclaration');
        }
        function variableUsedOutsideOfCodePath(variable) {
            return variableUsages.get(variable).size > 1;
        }
        function checkIdentifierUsage(node) {
            const { ref, variable } = resolveReference(node);
            if (ref) {
                processReference(ref);
            }
            if (variable) {
                updateVariableUsages(variable);
            }
        }
        function processReference(ref) {
            const assignmentStack = peek(codePathStack).assignmentStack;
            if (assignmentStack.length > 0) {
                const assignment = peek(assignmentStack);
                assignment.add(ref);
            }
            else {
                currentCodePathSegments.forEach(segment => {
                    const reachingDefs = reachingDefsForSegment(segment);
                    reachingDefs.add(ref);
                });
            }
        }
        function reachingDefsForSegment(segment) {
            let defs;
            if (reachingDefsMap.has(segment.id)) {
                defs = reachingDefsMap.get(segment.id);
            }
            else {
                defs = new helpers_1.ReachingDefinitions(segment);
                reachingDefsMap.set(segment.id, defs);
            }
            return defs;
        }
        function updateVariableUsages(variable) {
            const codePathId = peek(codePathStack).codePath.id;
            if (variableUsages.has(variable)) {
                variableUsages.get(variable).add(codePathId);
            }
            else {
                variableUsages.set(variable, new Set([codePathId]));
            }
        }
        function pushContext(codePathContext) {
            codePathStack.push(codePathContext);
        }
        function popContext() {
            codePathStack.pop();
        }
        function resolveReferenceRecursively(node, scope) {
            if (scope === null) {
                return { ref: null, variable: null };
            }
            const ref = scope.references.find(r => r.identifier === node);
            if (ref) {
                return { ref, variable: ref.resolved };
            }
            else {
                // if it's not a reference, it can be just declaration without initializer
                const variable = scope.variables.find(v => v.defs.find(def => def.name === node));
                if (variable) {
                    return { ref: null, variable };
                }
                // in theory we only need 1-level recursion, only for switch expression, which is likely a bug in eslint
                // generic recursion is used for safety & readability
                return resolveReferenceRecursively(node, scope.upper);
            }
        }
        function resolveReference(node) {
            return resolveReferenceRecursively(node, context.sourceCode.getScope(node));
        }
    },
};
class CodePathContext {
    constructor(codePath) {
        this.reachingDefinitionsMap = new Map();
        this.reachingDefinitionsStack = [];
        this.segments = new Map();
        this.assignmentStack = [];
        this.codePath = codePath;
    }
}
class AssignmentContext {
    constructor(node) {
        this.lhs = new Set();
        this.rhs = new Set();
        this.node = node;
    }
    isRhs(node) {
        return this.node.type === 'AssignmentExpression'
            ? this.node.right === node
            : this.node.init === node;
    }
    isLhs(node) {
        return this.node.type === 'AssignmentExpression'
            ? this.node.left === node
            : this.node.id === node;
    }
    add(ref) {
        let parent = ref.identifier;
        while (parent) {
            if (this.isLhs(parent)) {
                this.lhs.add(ref);
                break;
            }
            if (this.isRhs(parent)) {
                this.rhs.add(ref);
                break;
            }
            parent = parent.parent;
        }
        if (parent === null) {
            throw new Error('failed to find assignment lhs/rhs');
        }
    }
}
function peek(arr) {
    return arr[arr.length - 1];
}
function isSelfAssignement(ref) {
    const lhs = ref.resolved;
    if (ref.writeExpr?.type === 'Identifier') {
        const rhs = (0, helpers_1.getVariableFromIdentifier)(ref.writeExpr, ref.from);
        return lhs === rhs;
    }
    return false;
}
function isCompoundAssignment(writeExpr) {
    if (writeExpr?.hasOwnProperty('parent')) {
        const node = writeExpr.parent;
        return node && node.type === 'AssignmentExpression' && node.operator !== '=';
    }
    return false;
}
function isDefaultParameter(ref) {
    if (ref.identifier.type !== 'Identifier') {
        return false;
    }
    const parent = ref.identifier.parent;
    return parent && parent.type === 'AssignmentPattern';
}
//# sourceMappingURL=rule.js.map