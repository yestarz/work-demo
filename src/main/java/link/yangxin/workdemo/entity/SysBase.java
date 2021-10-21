/**
 *
 */
package link.yangxin.workdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import link.yangxin.workdemo.dto.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.Date;

/**
 * @author <a href="mailto:xuyy@yyt.com">Xu Yuanyuan</a>
 * @version 1.0
 * @date 2019年3月17日 下午4:43:49
 * @desc 系统管理基础类
 */
@ApiModel("系统对象基类")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysBase extends EntityBase {

	private static final long serialVersionUID = 769253799175569696L;

	/** 创建人Id */
	@ApiModelProperty("创建人Id")
	protected Long create_user_id;
	/** 创建人名称 */
	@ApiModelProperty("创建人名称")
	protected String create_user_name;
	/** 更新人ID */
	@ApiModelProperty("更新人ID")
	protected Long update_user_id;
	/** 更新人名称 */
	@ApiModelProperty("更新人名称")
	protected String update_user_name;


	public void create(Operator operator) {
		this.setCreate_user_id(operator.userId());
		this.setCreate_user_name(operator.username());
		this.setCreate_time(new Date());
		this.setUpdate_time(new Date());
		this.setEffect();
		this.update(operator);
	}

	public void update(Operator operator) {
		this.setUpdate_user_id(operator.userId());
		this.setUpdate_user_name(operator.username());
		this.setUpdate_time(new Date());
	}

	@Override
	public void delete(Operator operator) {
		super.delete(operator);
		this.setUpdate_user_id(operator.userId());
		this.setUpdate_user_name(operator.username());
	}
}
