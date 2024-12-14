package exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exchange.domain.ExchangeRateHistory;



@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Integer> {

	
}
