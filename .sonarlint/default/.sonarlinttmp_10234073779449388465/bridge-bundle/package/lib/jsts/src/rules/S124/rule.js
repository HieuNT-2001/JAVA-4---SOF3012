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
// https://sonarsource.github.io/rspec/#/rspec/S124/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, { schema: meta_1.schema }),
    create(context) {
        const options = context.options[0] || {};
        const flags = options.flags || '';
        const cleanedFlags = 'gimusy'
            .split('')
            .filter(c => flags.includes(c))
            .join('');
        const pattern = options.regularExpression
            ? new RegExp(options.regularExpression, cleanedFlags)
            : undefined;
        const message = options.message || 'The regular expression matches this comment.';
        return {
            'Program:exit': () => {
                context.sourceCode.getAllComments().forEach(comment => {
                    const rawTextTrimmed = comment.value.trim();
                    if (pattern?.test(rawTextTrimmed)) {
                        context.report({
                            message,
                            loc: comment.loc,
                        });
                    }
                });
            },
        };
    },
};
//# sourceMappingURL=rule.js.map