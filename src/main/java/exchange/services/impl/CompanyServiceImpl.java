package exchange.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.constants.Constants;
import exchange.domain.Company;
import exchange.domain.dto.CompanyDTO;
import exchange.repositories.CompanyRepository;
import exchange.services.CompanyService;
import exchange.services.exceptions.DataIntegrityViolationException;
import exchange.services.exceptions.ObjectNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired 
	CompanyRepository repository;
	
    @Autowired
    private ModelMapper mapper;
	
	
	@Override
	public Company findById(Integer id) {
		
		 Optional<Company>  company =   repository.findById(id);
		 return  company.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	@Override
	public List<Company> findAll() {
        return repository.findAll();
    }

    @Override
    public Company create(CompanyDTO obj) {
    	findByName(obj);
        return repository.save(mapper.map(obj, Company.class));
    }

    @Override
    public Company update(CompanyDTO obj) {
    	findByName(obj);
        return repository.save(mapper.map(obj, Company.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByName(CompanyDTO obj) {
        Optional<Company> Company = repository.findByName(obj.getName());
        if(Company.isPresent() && !Company.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException(Constants.EMPRESAJACADASTRADA);
        }
    }
}
