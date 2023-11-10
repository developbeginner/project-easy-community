package developbeginner.projecteasycommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("developbeginner.projecteasycommunity.dao")
@SpringBootApplication
public class ProjectEasyCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectEasyCommunityApplication.class, args);
    }

}
