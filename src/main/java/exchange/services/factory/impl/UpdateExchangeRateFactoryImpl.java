package exchange.services.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exchange.services.UpdateExchangeRateService;
import exchange.services.factory.UpdateExchangeRateFactory;
import exchange.services.impl.UpdateExchangeRateBRLImpl;
import exchange.services.impl.UpdateExchangeRateUSDImpl;

@Component
public class UpdateExchangeRateFactoryImpl implements UpdateExchangeRateFactory{
	
	private final  UpdateExchangeRateService updateExchangeRateBrlImpl;
	private final UpdateExchangeRateService  updateExchangeRateUSDmpl;
	
	@Autowired
	public  UpdateExchangeRateFactoryImpl(
			UpdateExchangeRateBRLImpl updateExchangeRateBrlImpl,
			UpdateExchangeRateUSDImpl updateExchangeRateUSDmpl) {
		
		this.updateExchangeRateBrlImpl = updateExchangeRateBrlImpl;
		this.updateExchangeRateUSDmpl = updateExchangeRateUSDmpl;
	}

	
	public  UpdateExchangeRateService createExchangeRate(String codeCoin) {
		
		switch (codeCoin) {
		case "BRL": {
			return updateExchangeRateBrlImpl;
			
		}
		case "USD": {
			return updateExchangeRateUSDmpl;
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + codeCoin);
		}
		
	};

}
