"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SONAR_CONTEXT = void 0;
exports.hasSonarContextOption = hasSonarContextOption;
const schema_1 = require("./helpers/schema");
/**
 * An internal rule parameter for context-passing support
 *
 * Rules implemented in the bridge all have access to the global
 * context since they share the same code base. However, external
 * rules like custom rules don't benefit from the same visibilty.
 *
 * To remedy this, rules that need to access the global context
 * for whatever reason can do so by first setting this parameter:
 *
 *
 * ```
 *  meta: {
 *    schema: [{
 *      title: 'sonar-context',
 *    }]
 *  }
 * ```
 *
 * The global context object can then be retrieved from the options
 * of ESLint's rule context, that is, `context.options`.
 */
exports.SONAR_CONTEXT = 'sonar-context';
/**
 * Checks if the rule schema sets the `sonar-context` internal parameter
 * @param ruleModule the rule definition
 * @param ruleId the ESLint rule key
 * @returns true if the rule definition includes the parameter
 */
function hasSonarContextOption(ruleModule, ruleId) {
    const schema = (0, schema_1.getRuleSchema)(ruleModule, ruleId);
    if (Array.isArray(schema)) {
        return schema.some(option => option.title === exports.SONAR_CONTEXT);
    }
    if (schema?.type === 'array' && Array.isArray(schema.items)) {
        return schema.items.some(option => option.title === exports.SONAR_CONTEXT);
    }
    return false;
}
//# sourceMappingURL=sonar-context.js.map