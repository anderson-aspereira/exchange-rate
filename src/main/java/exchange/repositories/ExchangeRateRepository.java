package exchange.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exchange.domain.ExchangeRate;



@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {

	Optional<ExchangeRate> findByCode(String code);
}
