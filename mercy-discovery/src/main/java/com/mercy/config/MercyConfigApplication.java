package com.mercy.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class MercyConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(MercyConfigApplication.class, args);

	}

}
