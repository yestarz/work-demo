package link.yangxin.workdemo.dto;


/**
 * 操作者
 *
 * @author yangxin
 * @date 2019/5/5
 */
public interface Operator {

    /**
     * 登陆用户id
     *
     * @return
     */
    Long userId();

    /**
     * 登录后的用户名字，不同于user_code
     *
     * @return
     */
    String username();

    /**
     * 用户类型
     *
     * @return
     * @see
     */
    Integer userType();

    /**
     * 操作者头像
     *
     * @return
     */
    String userIcon();

    /**
     * openId
     *
     * @return
     */
    String openId();

    /**
     * 是否已关注公众号
     *
     * @return
     */
    boolean isSubscribed();


}
