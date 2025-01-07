package exchange.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import exchange.config.RestTemplateClient;
import exchange.domain.ExchangeRate;
import exchange.domain.ExchangeRateExternal;
import exchange.domain.ExchangeRateHistory;
import exchange.repositories.ExchangeRateHistoryRepository;
import exchange.repositories.ExchangeRateRepository;
import exchange.services.UpdateExchangeRateService;
import exchange.services.externals.ApiService;

@Service
public abstract class UpdateExchangeRateImpl implements UpdateExchangeRateService{
	protected static final String HTTPS_ECONOMIA_AWESOMEAPI_COM_BR_LAST = "https://economia.awesomeapi.com.br/last/";

	public String codeCoin;
	
	@Autowired 
	ExchangeRateRepository  repository;
	
	@Autowired 
	 ExchangeRateHistoryRepository exchangeRateRepository;
	
   
    @Autowired
    protected RestTemplateClient restTemplateClient;

	
	@Override
	public void updateAll() {
		List<ExchangeRate> lstExchangeRate = this.repository.findListByCodeIn(this.codeCoin);
		if (!lstExchangeRate.isEmpty()) {
			String url = getUrlWithParameters(lstExchangeRate);
			ApiService apiService = new ApiService(restTemplateClient);
			String jsonString = apiService.callExternalApi(url);
			Map<String, ExchangeRateExternal> exchangeRateExternalMap = getExchangeRateExternalMap(jsonString);
			lstExchangeRate.stream()
					.forEach(e -> updateExchangeRate(e.getId(), e.getCode() + this.codeCoin, exchangeRateExternalMap));
		}
	}
	
	private void updateExchangeRate(Integer id, String key, Map<String, ExchangeRateExternal> exchangeRateExternalMap) {
		 Optional<ExchangeRate> exchangeRate =  this.repository.findById(id);
		 
		 if(exchangeRate.isPresent()) {
			 saveHistory(exchangeRate.get());
			 ExchangeRateExternal exchangeRateExternal =  exchangeRateExternalMap.get(key);
			 exchangeRate.get().setCurrentValue(exchangeRateExternal.getBid());
			 exchangeRate.get().setDate(LocalDateTime.parse(exchangeRateExternal.getCreate_date().replace(" ", "T")));
			 this.repository.save(exchangeRate.get());
		 }
	}
	

	
	private void saveHistory(ExchangeRate exchangeRate) {
		 ExchangeRateHistory   exchangeHistory   = new ExchangeRateHistory(null, exchangeRate.getCode(),  exchangeRate.getCodein(),   exchangeRate.getCurrentValue(),  exchangeRate.getDate(),  LocalDateTime.now()  );
		 exchangeRateRepository.save(exchangeHistory);
			
	}
	
	public abstract String  getUrlWithParameters(List<ExchangeRate>  lstExchangeRate);

	
	private Map<String, ExchangeRateExternal> getExchangeRateExternalMap(String jsonString){
		Gson gson = new Gson();  
		Type exchangeRateExternalMapType = new TypeToken<Map<String, ExchangeRateExternal>>() {}.getType();
		Map<String, ExchangeRateExternal> exchangeRateExternalMap = gson.fromJson(jsonString, exchangeRateExternalMapType);
		return exchangeRateExternalMap;
		
	}
	

}
