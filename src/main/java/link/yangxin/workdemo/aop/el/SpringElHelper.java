package link.yangxin.workdemo.aop.el;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;

/**
 * @author yangxin
 * @date 2021/10/18
 */
public class SpringElHelper {

    private static final ExpressionEvaluator<String> evaluator = new ExpressionEvaluator<>();

    /**
     * 解析EL表达式
     *
     * @param pjp
     * @param express
     * @return
     */
    public static String condition(ProceedingJoinPoint pjp, String express) {
        if (!express.startsWith("#")) {
            return express;
        }
        EvaluationContext evaluationContext = evaluator.createEvaluationContext(pjp.getTarget(), pjp.getTarget().getClass(), ((MethodSignature) pjp.getSignature()).getMethod(), pjp.getArgs());
        AnnotatedElementKey methodKey = new AnnotatedElementKey(((MethodSignature) pjp.getSignature()).getMethod(), pjp.getTarget().getClass());
        return evaluator.condition(express, methodKey, evaluationContext, String.class);
    }

}
