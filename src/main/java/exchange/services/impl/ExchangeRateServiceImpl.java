package exchange.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import exchange.config.RestTemplateClient;
import exchange.constants.Constants;
import exchange.domain.ExchangeRate;
import exchange.domain.ExchangeRateExternal;
import exchange.domain.ExchangeRateHistory;
import exchange.domain.dto.ExchangeRateDTO;
import exchange.repositories.ExchangeRateHistoryRepository;
import exchange.repositories.ExchangeRateRepository;
import exchange.services.ExchangeRateService;
import exchange.services.exceptions.DataIntegrityViolationException;
import exchange.services.exceptions.ObjectNotFoundException;
import exchange.services.externals.ApiService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private static final String HTTPS_ECONOMIA_AWESOMEAPI_COM_BR_LAST = "https://economia.awesomeapi.com.br/last/";

	@Autowired 
	ExchangeRateRepository repository;
	
	@Autowired 
	ExchangeRateHistoryRepository exchangeRateHistoryRepository;
	
	
    @Autowired
    private ModelMapper mapper;
	
    @Autowired
    private RestTemplateClient restTemplateClient;
	
	@Override
	public ExchangeRate findById(Integer id) {
		
		 Optional<ExchangeRate>  exchangeRate =   repository.findById(id);
		 return  exchangeRate.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	@Override
	public List<ExchangeRate> findAll() {
        return repository.findAll();
    }

    @Override
    public ExchangeRate create(ExchangeRateDTO obj) {
    	findByCode(obj);
        return repository.save(mapper.map(obj, ExchangeRate.class));
    }

    @Override
    public ExchangeRate update(ExchangeRateDTO obj) {
    	findByCode(obj);
        return repository.save(mapper.map(obj, ExchangeRate.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByCode(ExchangeRateDTO obj) {
        Optional<ExchangeRate> ExchangeRate = repository.findByCode(obj.getName());
        if(ExchangeRate.isPresent() && !ExchangeRate.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException(Constants.COINEXISTS);
        }
    }

	@Override
	public void updateAll() {
		
		List<ExchangeRate>  lstExchangeRate = findAll();
		String url = getUrlWithParameters(lstExchangeRate);
		ApiService apiService = new ApiService(restTemplateClient);
		String jsonString = apiService.callExternalApi(url);
		Map<String, ExchangeRateExternal> exchangeRateExternalMap = getExchangeRateExternalMap(jsonString);
		lstExchangeRate.stream().forEach(e -> updateExchangeRate(e.getId(), e.getCode()+e.getCodein(), exchangeRateExternalMap) );
		
	}
	
	private void updateExchangeRate(Integer id, String key, Map<String, ExchangeRateExternal> exchangeRateExternalMap) {
		 Optional<ExchangeRate>  exchangeRate =  repository.findById(id);
		 if(exchangeRate.isPresent()) {
			 saveHistory(exchangeRate.get());
			 ExchangeRateExternal exchangeRateExternal =  exchangeRateExternalMap.get(key);
			 exchangeRate.get().setCurrentValue(exchangeRateExternal.getBid());
			 exchangeRate.get().setDate(LocalDateTime.parse(exchangeRateExternal.getCreate_date().replace(" ", "T")));
			 repository.save( exchangeRate.get());
		 }
	}
	

	
	private void saveHistory(ExchangeRate exchangeRate) {
		 ExchangeRateHistory   exchangeHistory   = new ExchangeRateHistory(null, exchangeRate.getCode(),  exchangeRate.getCodein(),   exchangeRate.getCurrentValue(),  exchangeRate.getDate(),  LocalDateTime.now()  );
		 exchangeRateHistoryRepository.save(exchangeHistory);
			
	}
	
	private String  getUrlWithParameters(List<ExchangeRate>  lstExchangeRate) {
		
		StringBuilder sb = new StringBuilder();
		String parameters = lstExchangeRate.stream().map(e -> e.getCode() + "-" + e.getCodein() +",").collect(Collectors.joining());
		if(!parameters.isEmpty()) {
			parameters = parameters.substring(0, parameters.length()-1);
		}
		return  HTTPS_ECONOMIA_AWESOMEAPI_COM_BR_LAST + parameters ;
	}
	
	private Map<String, ExchangeRateExternal> getExchangeRateExternalMap(String jsonString){
		Gson gson = new Gson();  
		Type exchangeRateExternalMapType = new TypeToken<Map<String, ExchangeRateExternal>>() {}.getType();
		Map<String, ExchangeRateExternal> exchangeRateExternalMap = gson.fromJson(jsonString, exchangeRateExternalMapType);
		return exchangeRateExternalMap;
		
	}
}
