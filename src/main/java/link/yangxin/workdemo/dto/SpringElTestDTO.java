package link.yangxin.workdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangxin
 * @date 2021/10/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class SpringElTestDTO {

    private String id;

    private String name;

}
