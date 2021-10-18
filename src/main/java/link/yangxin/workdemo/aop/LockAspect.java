package link.yangxin.workdemo.aop;

import link.yangxin.workdemo.annotation.Lock;
import link.yangxin.workdemo.aop.el.ExpressionEvaluator;
import link.yangxin.workdemo.aop.el.SpringElHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2021/10/18
 */
@Component
@Aspect
public class LockAspect {

    protected static final Logger logger = LoggerFactory.getLogger(LockAspect.class);

    @Pointcut("@annotation(link.yangxin.workdemo.annotation.Lock)")
    public void pointcut() {

    }

    @Around("pointcut() && @annotation(lock)")
    public Object deal(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        String key = getFullKey(joinPoint, lock.key());
        logger.info("key is :{}", key);
        return joinPoint.proceed(joinPoint.getArgs());
    }


    /**
     * 获取完整的Key
     *
     * @param pjp
     * @param key
     * @key
     */
    private String getFullKey(ProceedingJoinPoint pjp, String key) {
        StringBuilder sb = new StringBuilder(pjp.getSignature().getDeclaringTypeName());
        sb.append(".").append(pjp.getSignature().getName()).append(".");
        sb.append(SpringElHelper.condition(pjp, key));
        return sb.toString();
    }

}
