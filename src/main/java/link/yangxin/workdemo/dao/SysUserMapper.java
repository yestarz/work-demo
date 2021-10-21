package link.yangxin.workdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import link.yangxin.workdemo.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangxin
 * @date 2021/10/20
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    Integer insertBatch(@Param("userList") List<SysUser> userList);

}
