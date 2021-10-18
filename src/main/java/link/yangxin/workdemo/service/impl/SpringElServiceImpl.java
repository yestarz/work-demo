package link.yangxin.workdemo.service.impl;

import link.yangxin.workdemo.annotation.Lock;
import link.yangxin.workdemo.dto.SpringElTestDTO;
import link.yangxin.workdemo.service.SpringElService;
import org.springframework.stereotype.Service;

/**
 * @author yangxin
 * @date 2021/10/18
 */
@Service
public class SpringElServiceImpl implements SpringElService {

    @Override
    @Lock(key = "#dto.id")
    public void test1(SpringElTestDTO dto) {

    }
}
