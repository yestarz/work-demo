package link.yangxin.workdemo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import link.yangxin.workdemo.dto.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author <a href="zap@cqyyt.com">Zhang shaoping</a>
 * @version 1.0
 * @date 2019年3月19日 下午1:04:02
 * @desc 用户管理表
 */
@TableName("sys_user_tab")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends SysBase implements Operator {

    private static final long serialVersionUID = 1L;

    public static final int SUBSCRIBE_YES = 1;

    public static final int SUBSCRIBE_NO = 0;

    public static final int REGISTER_SOURCE_YUNYUTONG = 0;

    public static final int REGISTER_SOURCE_APP = 1;

    // 用户登录名
    private String user_code;

    // 用户昵称
    private String user_nick;

    // 用户姓名
    private String user_name;

    // 男/女
    private String user_sex;

    // 出生日期
    private Timestamp user_birth;

    // 登录密码
    private String user_pass;

    // 用户电话
    private String user_phone;

    // 用户图标
    private String user_icon;

    // 用户openId
    private String user_open_id;

    // 身份证号
    private String idcard_no;

    // 末次月经
    private Date last_menstrual_date;

    // 注册时间
    private Date register_time;

    // 省编码
    private String province_code;

    // 省名称
    private String province_name;

    // 市编码
    private String city_code;

    // 市名称
    private String city_name;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 现住址
    private String address;

    // 账号创建方式，0：系统自动创建，1：用户注册 2
    private int sys_create;

    // 最后登录时间
    private Date last_login_time;

    private String union_id;

    // 公众号openid
    private String mp_open_id;

    @ApiModelProperty("是否已经关注 0未关注 1已关注")
    private Integer subscribe;

    @ApiModelProperty("默认医院id")
    private Long default_hp_id;

    /**
     * 注册来源
     * 0孕育通微信公众号
     * 1APP
     */
    private Integer register_source;

    @ApiModelProperty("是否禁用 ")
    private Integer is_disable;

    @ApiModelProperty("所在地区")
    private String area;

    @ApiModelProperty("开放平台openID")
    private String open_plat_open_id;

    @ApiModelProperty("登录授权码")
    private String login_auth_code;

    @ApiModelProperty("授权码时间戳")
    private Long login_auth_timestamp;

    @ApiModelProperty("关注时间")
    private Date subscribe_time;

    @ApiModelProperty("取消关注时间")
    private Date unsubscribe_time;

    @ApiModelProperty("苹果用户标识")
    private String apple_user_identifier;


    @Override
    public Long userId() {
        return getId();
    }

    @Override
    public String username() {
        return getUser_nick();
    }

    @Override
    public Integer userType() {
        return 0;
    }

    @Override
    public String userIcon() {
        return getUser_icon();
    }

    @Override
    public String openId() {
        return user_open_id;
    }

    @Override
    public boolean isSubscribed() {
        return false;
    }


}
