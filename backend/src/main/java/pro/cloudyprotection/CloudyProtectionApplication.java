package pro.cloudyprotection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CloudyProtectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudyProtectionApplication.class, args);
    }
}