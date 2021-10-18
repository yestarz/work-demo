package link.yangxin.workdemo;

import link.yangxin.workdemo.dto.SpringElTestDTO;
import link.yangxin.workdemo.service.SpringElService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2021/10/18
 */
public class SpringElTest extends WorkDemoApplicationTests{

    @Resource
    private SpringElService springElService;

    @Test
    public void test1(){
        springElService.test1(new SpringElTestDTO("111","sdf"));
        springElService.test1(new SpringElTestDTO("222","sdf"));
    }

}
