package sender.configs;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "email-sender")
public class AppProperties {
    private final String emailFrom;
    private final String password;
    private final String host;
}
