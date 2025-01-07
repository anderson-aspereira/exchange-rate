package exchange.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import exchange.domain.ExchangeRate;
import exchange.domain.ExchangeRateHistory;



@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {

	Optional<ExchangeRate> findByCode(String code);
	 @Query("SELECT e FROM ExchangeRate e WHERE e.codein = :codein")
	 List<ExchangeRate> findListByCodeIn(String codein);
}
