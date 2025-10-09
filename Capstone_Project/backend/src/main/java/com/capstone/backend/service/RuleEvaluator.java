package com.capstone.backend.service;

import com.capstone.backend.model.Job;
import com.capstone.backend.model.Rule;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RuleEvaluator {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final JobVariableExtractor variableExtractor;

    public RuleEvaluator(JobVariableExtractor variableExtractor) {
        this.variableExtractor = variableExtractor;
    }

    public boolean evaluate(Rule rule, Job job) {
        Map<String, Object> vars = variableExtractor.extract(job);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariables(vars);

        Expression exp = parser.parseExpression(rule.getExpression());
        Boolean result = exp.getValue(context, Boolean.class);

        return Boolean.TRUE.equals(result);
    }
}
