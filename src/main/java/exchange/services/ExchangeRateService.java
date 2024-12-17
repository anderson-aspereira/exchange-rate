package exchange.services;

import java.util.List;

import exchange.domain.ExchangeRate;
import exchange.domain.dto.ExchangeRateDTO;



public interface ExchangeRateService {
	
	public ExchangeRate findById(Integer id);
	public  List<ExchangeRate> findAll();
	public ExchangeRate create(ExchangeRateDTO obj);
	public ExchangeRate update(ExchangeRateDTO obj);
	public  void updateAll();
	public  void delete(Integer id);

}
