package exchange.services;

import java.util.List;

import exchange.domain.ExchangeRateHistory;
import exchange.domain.dto.ExchangeRateHistoryDTO;



public interface ExchangeRateHistoryService {
	
	public List<ExchangeRateHistory> findAllHistoryByIdExchangeRate(Integer id);
	public ExchangeRateHistory findById(Integer id);
	public  List<ExchangeRateHistory> findAll();
	public ExchangeRateHistory create(ExchangeRateHistory obj);
	public ExchangeRateHistory update(ExchangeRateHistory obj);
	public  void delete(Integer id);

}
