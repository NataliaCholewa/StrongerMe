package strongerme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
    exclude = {
        org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
    }
)
@ComponentScan(basePackages = "strongerme")
public class StrongerMeApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrongerMeApplication.class, args);
    }
}

