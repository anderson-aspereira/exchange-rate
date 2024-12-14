package exchange.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.domain.ExchangeRateHistory;
import exchange.domain.dto.ExchangeRateHistoryDTO;
import exchange.repositories.ExchangeRateHistoryRepository;
import exchange.services.ExchangeRateHistoryService;
import exchange.services.exceptions.ObjectNotFoundException;

@Service
public class ExchangeRateHistoryServiceImpl implements ExchangeRateHistoryService {

	@Autowired 
	ExchangeRateHistoryRepository repository;
	
    @Autowired
    private ModelMapper mapper;
	
	
	@Override
	public ExchangeRateHistory findById(Integer id) {
		
		 Optional<ExchangeRateHistory>  company =   repository.findById(id);
		 return  company.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	@Override
	public List<ExchangeRateHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public ExchangeRateHistory create(ExchangeRateHistoryDTO obj) {
    	return repository.save(mapper.map(obj, ExchangeRateHistory.class));
    }

    @Override
    public ExchangeRateHistory update(ExchangeRateHistoryDTO obj) {
    	return repository.save(mapper.map(obj, ExchangeRateHistory.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }


}
