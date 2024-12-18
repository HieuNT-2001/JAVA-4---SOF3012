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
// https://sonarsource.github.io/rspec/#/rspec/S2703/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const excludedNames = new Set((0, helpers_1.flatMap)(Object.values(helpers_1.globalsByLibraries), globals => globals));
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        messages: {
            explicitModifier: 'Add the "let", "const" or "var" keyword to this declaration of "{{variable}}" to make it explicit.',
        },
    }),
    create(context) {
        return {
            'Program:exit'(node) {
                const globalScope = context.sourceCode.getScope(node);
                const alreadyReported = new Set();
                globalScope.through
                    .filter(ref => ref.isWrite())
                    .forEach(ref => {
                    const name = ref.identifier.name;
                    if (!alreadyReported.has(name) && !excludedNames.has(name)) {
                        alreadyReported.add(name);
                        context.report({
                            messageId: 'explicitModifier',
                            data: {
                                variable: name,
                            },
                            node: ref.identifier,
                        });
                    }
                });
            },
        };
    },
};
//# sourceMappingURL=rule.js.map