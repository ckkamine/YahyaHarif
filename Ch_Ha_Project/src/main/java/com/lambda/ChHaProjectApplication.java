package com.lambda;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lambda.security.SaltedSHA256PasswordEncoder;


/**
 * @author Choukoukou Amine & Yahya
 *
 */
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class ChHaProjectApplication {

    public static void main(String[] args) {
    	ApplicationContext applicationContext= SpringApplication.run(ChHaProjectApplication.class, args);
    	applicationContext.getBean(DataBaseInit.class).init();
    }
}
class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ChHaProjectApplication.class);
	}
	



}
@EnableSpringDataWebSupport
@EnableTransactionManagement
@Configuration
class CommonConfiguration {

	@Bean
	public SaltedSHA256PasswordEncoder saltedSHA256PasswordEncoder(
			Environment environment) throws NoSuchAlgorithmException {
		return new SaltedSHA256PasswordEncoder(
				environment.getProperty("secret"));
	}
}
