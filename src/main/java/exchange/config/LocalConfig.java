package exchange.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import exchange.domain.Company;
import exchange.repositories.CompanyRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private  CompanyRepository companyRepository;

    @Bean
    Integer startDB() {

		Company company = new Company(null, "Acme", "acme@acme.com");
		Company company2 = new Company(null, "FFF", "contact@fff.com");

		 companyRepository.saveAll(List.of(company, company2));
		 
		return Integer.MIN_VALUE;

	}

}
