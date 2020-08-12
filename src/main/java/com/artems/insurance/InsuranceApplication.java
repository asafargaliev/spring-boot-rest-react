package com.artems.insurance;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);
		return mapper;
	}

}
