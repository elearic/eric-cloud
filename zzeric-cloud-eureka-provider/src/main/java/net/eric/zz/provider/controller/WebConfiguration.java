package net.eric.zz.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ace
 * @date 2017/9/8
 */
@Primary
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private AricRequestBodyAdviceAdapter aricRequestBodyAdviceAdapter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(aricRequestBodyAdviceAdapter);
	}

}
