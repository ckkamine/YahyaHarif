package com.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableCaching
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
