package com.kyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.kyn"})
@MapperScan("com.kyn.sequencegeneratebydb.dal.mapper")

public class SequenceGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SequenceGenerateApplication.class, args);
    }

}
