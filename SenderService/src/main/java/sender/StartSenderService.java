package sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sender.configs.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class StartSenderService {
    public static void main(String[] args) {
        SpringApplication.run(StartSenderService.class,args);
    }

}
