package link.yangxin.workdemo.aop.el;

/**
 * @author yangxin
 * @date 2021/10/18
 */
public class ExpressionRootObject {

    private final Object object;
    private final Object[] args;

    public ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }

}
