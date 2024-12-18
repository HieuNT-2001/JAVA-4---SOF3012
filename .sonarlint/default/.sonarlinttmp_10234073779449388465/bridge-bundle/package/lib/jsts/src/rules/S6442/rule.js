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
// https://sonarsource.github.io/rspec/#/rspec/S6442/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const REACT_MODULE = 'react';
const REACT_PATTERN = /^[^a-z]/;
const HOOK_FUNCTION = 'useState';
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        messages: {
            noHookSetterInBody: 'Remove this state setter call, perhaps move it to an event handler or JSX attribute',
        },
    }),
    create(context) {
        function isHookCall(node) {
            return ((0, helpers_1.getFullyQualifiedName)(context, node) === `${REACT_MODULE}.${HOOK_FUNCTION}` &&
                node.arguments.length === 1);
        }
        function getReactComponentScope(node) {
            const scope = context.sourceCode.getScope(node);
            const isReact = (0, helpers_1.isFunctionNode)(scope.block) && matchesReactComponentName(scope.block, 1);
            return isReact ? scope : null;
        }
        function isInsideFunctionScope(scope, node) {
            function searchUpperFunctionScope(current) {
                if (current === null) {
                    return null;
                }
                else if (current.type === 'function') {
                    return current;
                }
                else {
                    return searchUpperFunctionScope(current.upper);
                }
            }
            return (scope !== null && searchUpperFunctionScope(context.sourceCode.getScope(node)) === scope);
        }
        function isInsideConditional(node) {
            return ((0, helpers_1.findFirstMatchingLocalAncestor)(node, n => n.type === 'IfStatement') !==
                undefined);
        }
        let reactComponentScope; // Scope of the React component render function.
        const setters = []; // Setter variables returned by the React useState() function.
        return {
            ':function'(node) {
                reactComponentScope ??= getReactComponentScope(node); // Store the top-most React component scope.
            },
            ':function:exit'(node) {
                if (context.sourceCode.getScope(node) === reactComponentScope) {
                    // Clean variables when leaving the React component scope.
                    reactComponentScope = null;
                    setters.length = 0;
                }
            },
            // Selector matching declarations like: const [count, setCount] = useState(0);
            ['VariableDeclarator[init.type="CallExpression"]' +
                ':has(ArrayPattern[elements.length=2][elements.0.type="Identifier"][elements.1.type="Identifier"])'](node) {
                if (!isInsideFunctionScope(reactComponentScope, node)) {
                    return;
                }
                const hookDeclarator = node;
                if (isHookCall(hookDeclarator.init)) {
                    const variable = (0, helpers_1.getVariableFromName)(context, hookDeclarator.id.elements[1].name, node);
                    if (variable != null) {
                        setters.push(variable);
                    }
                }
            },
            // Selector matching function calls like: setCount(1)
            'CallExpression[callee.type="Identifier"][arguments.length=1]'(node) {
                if (!isInsideFunctionScope(reactComponentScope, node) ||
                    setters.length === 0 ||
                    isInsideConditional(node)) {
                    return;
                }
                const maybeSetterCall = node;
                const calleeVariable = (0, helpers_1.getVariableFromName)(context, maybeSetterCall.callee.name, node);
                if (setters.some(variable => variable === calleeVariable)) {
                    context.report({
                        messageId: 'noHookSetterInBody',
                        node: node.callee,
                    });
                }
            },
        };
    },
};
function hasParent(node) {
    return node.parent != null;
}
function matchesReactComponentName(node, max = 0) {
    if (node == null) {
        return false;
    }
    else if ((0, helpers_1.isIdentifier)(node)) {
        return REACT_PATTERN.test(node.name);
    }
    else if (node.type === 'FunctionDeclaration') {
        return matchesReactComponentName(node.id);
    }
    else if (node.type === 'VariableDeclarator') {
        return matchesReactComponentName(node.id);
    }
    else if (node.type === 'AssignmentExpression') {
        return matchesReactComponentName(node.left);
    }
    else if (node.type === 'MemberExpression') {
        return matchesReactComponentName(node.property);
    }
    else if (hasParent(node) && max > 0) {
        return matchesReactComponentName(node.parent, max - 1);
    }
    else {
        return false;
    }
}
//# sourceMappingURL=rule.js.map