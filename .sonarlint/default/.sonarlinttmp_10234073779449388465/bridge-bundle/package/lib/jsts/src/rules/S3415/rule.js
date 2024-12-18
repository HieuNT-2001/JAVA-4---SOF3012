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
// https://sonarsource.github.io/rspec/#/rspec/S3415/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const ASSERT_FUNCTIONS = [
    'equal',
    'notEqual',
    'strictEqual',
    'notStrictEqual',
    'deepEqual',
    'notDeepEqual',
    'closeTo',
    'approximately',
];
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, { hasSuggestions: true }, true),
    create(context) {
        const testCases = [];
        return {
            CallExpression(node) {
                if (helpers_1.Mocha.isTestCase(node)) {
                    testCases.push(node);
                    return;
                }
                if (testCases.length > 0) {
                    checkInvertedArguments(node, context);
                }
            },
            'CallExpression:exit': (node) => {
                if (helpers_1.Mocha.isTestCase(node)) {
                    testCases.pop();
                }
            },
        };
    },
};
function checkInvertedArguments(node, context) {
    const args = extractAssertionsArguments(node);
    if (args) {
        const [actual, expected, format] = args;
        if ((0, helpers_1.isLiteral)(actual) && !(0, helpers_1.isLiteral)(expected)) {
            (0, helpers_1.report)(context, {
                node: expected,
                message: `Swap these 2 arguments so they are in the correct order: ${format}.`,
                suggest: [
                    {
                        desc: 'Swap arguments',
                        fix: fixer => [
                            fixer.replaceText(actual, context.sourceCode.getText(expected)),
                            fixer.replaceText(expected, context.sourceCode.getText(actual)),
                        ],
                    },
                ],
            }, [(0, helpers_1.toSecondaryLocation)(actual, 'Other argument to swap.')]);
        }
    }
}
function extractAssertionsArguments(node) {
    return extractAssertArguments(node) ?? extractExpectArguments(node) ?? extractFailArguments(node);
}
function extractAssertArguments(node) {
    if ((0, helpers_1.isMethodCall)(node) && node.arguments.length > 1) {
        const { callee: { object, property }, arguments: [actual, expected], } = node;
        if ((0, helpers_1.isIdentifier)(object, 'assert') && (0, helpers_1.isIdentifier)(property, ...ASSERT_FUNCTIONS)) {
            return [actual, expected, `${object.name}.${property.name}(actual, expected)`];
        }
    }
    return null;
}
function extractExpectArguments(node) {
    if (node.callee.type !== 'MemberExpression') {
        return null;
    }
    let { object, property } = node.callee;
    if (!(0, helpers_1.isIdentifier)(property, 'equal', 'eql', 'closeTo')) {
        return null;
    }
    while (object.type === 'MemberExpression') {
        object = object.object;
    }
    if (object.type === 'CallExpression' && (0, helpers_1.isIdentifier)(object.callee, 'expect')) {
        return [
            object.arguments[0],
            node.arguments[0],
            `${object.callee.name}(actual).to.${property.name}(expected)`,
        ];
    }
    return null;
}
function extractFailArguments(node) {
    if ((0, helpers_1.isMethodCall)(node) && node.arguments.length > 1) {
        const { callee: { object, property }, arguments: [actual, expected], } = node;
        if ((0, helpers_1.isIdentifier)(object, 'assert', 'expect', 'should') && (0, helpers_1.isIdentifier)(property, 'fail')) {
            return [actual, expected, `${object.name}.${property.name}(actual, expected)`];
        }
    }
    return null;
}
//# sourceMappingURL=rule.js.map