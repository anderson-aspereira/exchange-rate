package exchange.services;

import java.util.List;

import exchange.domain.ExchangeRateHistory;
import exchange.domain.dto.ExchangeRateHistoryDTO;



public interface ExchangeRateHistoryService {
	
	public ExchangeRateHistory findById(Integer id);
	public  List<ExchangeRateHistory> findAll();
	public ExchangeRateHistory create(ExchangeRateHistoryDTO obj);
	public ExchangeRateHistory update(ExchangeRateHistoryDTO obj);
	public  void delete(Integer id);

}
