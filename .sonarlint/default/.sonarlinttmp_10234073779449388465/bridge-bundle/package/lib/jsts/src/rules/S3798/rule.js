"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        messages: {
            defineLocally: 'Define this declaration in a local scope or bind explicitly the property to the global object.',
        },
    }),
    create(context) {
        return {
            Program(node) {
                const scope = context.sourceCode.getScope(node);
                // As we parse every file with "module" source type, we find user defined global variables in the module scope
                const moduleScope = findModuleScope(context);
                moduleScope?.variables.forEach(variable => {
                    if (scope.variables.find(global => global.name === variable.name)) {
                        // Avoid reporting on redefinitions of actual global variables
                        return;
                    }
                    for (const def of variable.defs) {
                        const defNode = def.node;
                        if (def.type === 'FunctionName' ||
                            (def.type === 'Variable' && def.parent?.kind === 'var' && !isRequire(def.node.init))) {
                            context.report({
                                node: defNode,
                                messageId: 'defineLocally',
                            });
                            return;
                        }
                    }
                });
            },
        };
    },
};
function findModuleScope(context) {
    return context.sourceCode.scopeManager.scopes.find(s => s.type === 'module');
}
function isRequire(node) {
    return (node?.type === 'CallExpression' &&
        node.arguments.length === 1 &&
        (0, helpers_1.isIdentifier)(node.callee, 'require'));
}
//# sourceMappingURL=rule.js.map