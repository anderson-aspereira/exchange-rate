package exchange.services;

import java.util.List;

import exchange.domain.Company;
import exchange.domain.dto.CompanyDTO;



public interface CompanyService {
	
	public Company findById(Integer id);
	public  List<Company> findAll();
	public Company create(CompanyDTO obj);
	public Company update(CompanyDTO obj);
	public  void delete(Integer id);

}
