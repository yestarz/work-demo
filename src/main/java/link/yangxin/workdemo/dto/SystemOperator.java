package link.yangxin.workdemo.dto;

/**
 * @author yangxin
 * @date 2019/8/5
 */
public class SystemOperator implements Operator {

    public static SystemOperator newInstance() {
        return new SystemOperator();
    }

    public enum Instance {
        INSTANCE;
        private final SystemOperator systemOperator;

        Instance() {
            this.systemOperator = SystemOperator.newInstance();
        }

        public SystemOperator getInstance() {
            return this.systemOperator;
        }

    }

    @Override
    public Long userId() {
        return -1L;
    }

    @Override
    public String username() {
        return "system";
    }

    @Override
    public Integer userType() {
        return -1;
    }

    @Override
    public String userIcon() {
        return null;
    }

    @Override
    public String openId() {
        return null;
    }

    @Override
    public boolean isSubscribed() {
        return true;
    }
}
