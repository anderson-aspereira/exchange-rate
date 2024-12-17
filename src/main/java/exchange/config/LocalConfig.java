package exchange.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import exchange.domain.Company;
import exchange.domain.ExchangeRate;
import exchange.domain.ExchangeRateHistory;
import exchange.repositories.CompanyRepository;
import exchange.repositories.ExchangeRateHistoryRepository;
import exchange.repositories.ExchangeRateRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private  CompanyRepository companyRepository;
	
	@Autowired
	private  ExchangeRateRepository exchangeRateRepository;
	
	@Autowired
	private  ExchangeRateHistoryRepository exchangeRateHistoryRepository;

    @Bean
    Integer startDB() {

		Company company = new Company(null, "Acme", "acme@acme.com");
		Company company2 = new Company(null, "FFF", "contact@fff.com");

		 companyRepository.saveAll(List.of(company, company2));
		 
		 
		 ExchangeRate exchangeUSD = new ExchangeRate(null, "USD", "BRL", "Dólar Americano/Real Brasileiro", 6.0419, LocalDateTime.parse("2024-12-13T20:59:58"));
		 ExchangeRate exchangeEUR = new ExchangeRate(null, "EUR", "BRL", "Euro/Real Brasileiro", 6.3371, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeBTC = new ExchangeRate(null, "BTC", "BRL", "Bitcoin/Real Brasileiro", 663475D, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeCAD = new ExchangeRate(null, "CAD", "BRL", "Dólar Canadense/Real Brasileiro", 4.3157, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeGBP = new ExchangeRate(null, "GBP", "BRL", "Libra Esterlina/Real Brasileiro", 7.8186, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeMXN = new ExchangeRate(null, "MXN", "BRL", "Peso Mexicano/Real Brasileiro",0.3056, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeAUD = new ExchangeRate(null, "AUD", "BRL", "Dólar Australiano/Real Brasileiro", 3.9138, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeCHF = new ExchangeRate(null, "CHF", "BRL", "Franco Suíço/Real Brasileiro", 6.8835, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeJPY = new ExchangeRate(null, "JPY", "BRL", "Iene Japonês/Real Brasileiro", 6.8835, LocalDateTime.parse("2024-12-13T18:59:45"));
		 ExchangeRate exchangeNZD = new ExchangeRate(null, "NZD", "BRL", "Dólar Neozelandês/Real Brasileiro", 3.5575, LocalDateTime.parse("2024-12-13T18:59:45"));
		 
				
		 exchangeRateRepository.saveAll(List.of(exchangeUSD, exchangeEUR, exchangeBTC, exchangeCAD,exchangeGBP, exchangeMXN,exchangeAUD, exchangeCHF,exchangeJPY, exchangeNZD ));
		 
		 ExchangeRateHistory   exchangeHistoryUSD1   = new ExchangeRateHistory(null, "USD", "BRL",  6.0001, LocalDateTime.parse("2024-12-11T17:59:58"),  LocalDateTime.now()  );
		 ExchangeRateHistory   exchangeHistoryUSD2   = new ExchangeRateHistory(null, "USD", "BRL",  6.0854, LocalDateTime.parse("2024-12-12T19:59:58"),  LocalDateTime.now()  );
		 	
		 
		 		
		 exchangeRateHistoryRepository.saveAll(List.of(exchangeHistoryUSD1, exchangeHistoryUSD2));
		 
		 
		 
		return Integer.MIN_VALUE;

	}

}
