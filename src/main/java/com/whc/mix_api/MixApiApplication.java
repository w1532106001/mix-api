package com.whc.mix_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author whc
 */
@SpringBootApplication
@MapperScan("com.whc.mix_api.mapper")
public class MixApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MixApiApplication.class, args);
    }

}
