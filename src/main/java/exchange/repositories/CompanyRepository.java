package exchange.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import exchange.domain.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findByName(String name);
}
