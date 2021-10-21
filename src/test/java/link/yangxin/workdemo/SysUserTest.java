package link.yangxin.workdemo;

import com.google.common.collect.Lists;
import link.yangxin.workdemo.dao.SysUserMapper;
import link.yangxin.workdemo.dto.Operator;
import link.yangxin.workdemo.dto.SystemOperator;
import link.yangxin.workdemo.entity.SysUser;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author yangxin
 * @date 2021/10/20
 */
public class SysUserTest extends WorkDemoApplicationTests{

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void test1(){
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(sysUsers.size());
    }

    /**
     * 现在测试环境用户有 25354 个
     */
    @Test
    public void testInsert(){
        Operator operator = new SystemOperator();
        int start = 1;

        List<SysUser> list = new ArrayList<>();
        for (int i = 0; i < 30_0000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUser_code("user_code_" + start);
            sysUser.setUser_nick("user_nick_" + start);
            sysUser.setUser_name("user_name_" + start);
            sysUser.setUser_open_id("open_id_" + start);

            sysUser.setRegister_time(new Date());
            sysUser.setSys_create(-1);
            sysUser.setUnion_id("unionId_" + start);
            sysUser.setSubscribe(0);
            sysUser.setRegister_source(-1);
            sysUser.setIs_disable(0);
            sysUser.create(operator);

            list.add(sysUser);
            start++;
        }

        List<List<SysUser>> partition = Lists.partition(list, 2000);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        List<Future<Integer>> futureList = new ArrayList<>();

        for (List<SysUser> userList : partition) {
            Future<Integer> future =  executorService.submit(()->{
                return sysUserMapper.insertBatch(userList);
            });
            futureList.add(future);
        }

        for (Future<Integer> future : futureList) {
            try {
                Integer integer = future.get();
                System.out.println("成功增加了" + integer + "条数据");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
