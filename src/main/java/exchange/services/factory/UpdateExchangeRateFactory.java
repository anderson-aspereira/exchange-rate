package exchange.services.factory;

import exchange.services.UpdateExchangeRateService;

public interface UpdateExchangeRateFactory {
	
	 public UpdateExchangeRateService createExchangeRate(String codeCoin);

}
