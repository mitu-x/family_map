package org.dbWandy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.dbWandy.mapper")
public class FamilyMapApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FamilyMapApplication.class);
        app.run(args);
    }
}
