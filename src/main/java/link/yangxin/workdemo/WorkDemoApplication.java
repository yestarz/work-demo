package link.yangxin.workdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("link.yangxin.workdemo.dao")
public class WorkDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkDemoApplication.class, args);
    }

}
