package com.sony.mts;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: App
 * @Description: application启动类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:38:18
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.sony.mts")
public class App {

	public static void main(String[] args) {
		log.info(properties.getProperty("SpringBootStartloading"));
		SpringApplication.run(App.class, args);
		log.info(properties.getProperty("SpringBootLoaded"));
	}

}
