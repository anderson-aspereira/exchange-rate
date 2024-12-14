package exchange.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.constants.Constants;
import exchange.domain.ExchangeRate;
import exchange.domain.dto.ExchangeRateDTO;
import exchange.repositories.ExchangeRateRepository;
import exchange.services.ExchangeRateService;
import exchange.services.exceptions.DataIntegrityViolationException;
import exchange.services.exceptions.ObjectNotFoundException;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired 
	ExchangeRateRepository repository;
	
    @Autowired
    private ModelMapper mapper;
	
	
	@Override
	public ExchangeRate findById(Integer id) {
		
		 Optional<ExchangeRate>  company =   repository.findById(id);
		 return  company.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
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
}
