package vn.iotstar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import vn.iotstar.config.StorageProperties;
import vn.iotstar.service.IStorageService;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class SpringbootRestApiAjaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiAjaxApplication.class, args);
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> storageService.init());
	}
}