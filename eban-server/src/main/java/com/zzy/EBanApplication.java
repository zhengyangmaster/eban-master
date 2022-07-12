package com.zzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.zzy.eban.mapper")
//@EnableScheduling
public class EBanApplication {
    public static void main(String[] args) {
        SpringApplication.run(EBanApplication.class, args);
    }
}
