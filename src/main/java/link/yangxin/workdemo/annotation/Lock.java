package link.yangxin.workdemo.annotation;

import java.lang.annotation.*;

/**
 * @author yangxin
 * @date 2021/10/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

    String key() default "";

}
