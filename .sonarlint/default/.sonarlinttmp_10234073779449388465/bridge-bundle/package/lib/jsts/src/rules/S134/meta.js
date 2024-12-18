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
Object.defineProperty(exports, "__esModule", { value: true });
exports.schema = exports.sonarKey = exports.meta = void 0;
// DO NOT EDIT! This file is autogenerated by "npm run generate-meta"
exports.meta = {
    type: 'suggestion',
    docs: {
        description: 'Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply',
        recommended: false,
        url: 'https://sonarsource.github.io/rspec/#/rspec/S134/javascript',
        requiresTypeChecking: false,
    },
};
exports.sonarKey = 'S134';
exports.schema = {
    type: 'array',
    minItems: 0,
    maxItems: 2,
    items: [
        {
            type: 'object',
            properties: {
                maximumNestingLevel: {
                    type: 'integer',
                },
            },
            additionalProperties: false,
        },
    ],
};
//# sourceMappingURL=meta.js.map