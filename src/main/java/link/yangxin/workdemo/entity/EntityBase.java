/**
 *
 */
package link.yangxin.workdemo.entity;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import link.yangxin.workdemo.dto.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:xuyy@yyt.com">Xu Yuanyuan</a>
 * @version 1.0
 * @date 2019年3月17日 下午4:38:37
 * @desc EntityBase
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class EntityBase implements Serializable {

    private static final long serialVersionUID = 6013100344516308988L;

    /** 主键 */
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    protected Long id;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    protected Date create_time;

    /** 更新时间 */
    @ApiModelProperty("修改时间")
    protected Date update_time;

    /** 状态 */
    @ApiModelProperty("状态0无效 1有效")
    protected Integer status;

    public void delete(Operator operator) {
        this.setUpdate_time(new Date());
        this.setStatus(1);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @ApiModelProperty(hidden = true)
    public boolean isEdit() {
        return id != null && id > 0;
    }

    public void setEffect() {
        this.setStatus(1);
    }


}
