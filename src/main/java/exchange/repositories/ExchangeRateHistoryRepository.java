package exchange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import exchange.domain.ExchangeRateHistory;



@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Integer> {
	
	 @Query("SELECT a FROM ExchangeRateHistory a WHERE a.code = :code AND a.codein = :codein")
	    List<ExchangeRateHistory> findByCode(String code, String codein);
	 
	 
}
