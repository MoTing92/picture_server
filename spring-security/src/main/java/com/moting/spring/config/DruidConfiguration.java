package com.moting.spring.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfiguration {

	@Bean
	public ServletRegistrationBean statViewServlete(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet() , "/druid/*");
		servletRegistrationBean.addInitParameter("allow", "192.168.216.74,127.0.0.1");
		servletRegistrationBean.addInitParameter("deny", "192.168.216.100");
		servletRegistrationBean.addInitParameter("loginUsername", "moting");
		servletRegistrationBean.addInitParameter("loginPassword", "moting");
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	
	public FilterRegistrationBean statFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter(), statViewServlete());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
