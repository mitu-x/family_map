package org.dbWandy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FamilyMapApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FamilyMapApplication.class);
        app.run(args);
        log.info("项目已启动...");
    }
}
