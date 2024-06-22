package com.groupware.orca;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class OrcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcaApplication.class, args);
	}

}
