package com.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 项目启动类
 * @author Administrator
 *
 */
@SpringBootApplication
@MapperScan("com.shop.mapper")
public class OnlineStoreApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}
	
	/**
	 * 转发json前台406问题解决
	 */
	@Override
	public void  configureContentNegotiation(ContentNegotiationConfigurer configurer){
		configurer.favorPathExtension(false);
	}
}
