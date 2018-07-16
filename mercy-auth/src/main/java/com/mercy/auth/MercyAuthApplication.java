package com.mercy.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.mercy.auth", "com.mercy.common.bean"})
public class MercyAuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(MercyAuthApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dateSource() {
//		DruidDataSource dataSource = new DruidDataSource();
//		return dataSource;
//	}
}
