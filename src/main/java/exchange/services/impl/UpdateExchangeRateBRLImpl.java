package exchange.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.domain.ExchangeRate;
import exchange.services.ExchangeRateHistoryService;
import exchange.services.ExchangeRateService;

@Service
public class UpdateExchangeRateBRLImpl extends UpdateExchangeRateImpl{

	
	
	public UpdateExchangeRateBRLImpl() {
		super.codeCoin = "BRL";
	}

	@SuppressWarnings("static-access")
	@Override
	public  String  getUrlWithParameters(List<ExchangeRate>  lstExchangeRate) {
		
		StringBuilder sb = new StringBuilder();
		String parameters = lstExchangeRate.stream().map(e -> e.getCode() + "-" + e.getCodein() +",").collect(Collectors.joining());
		if(!parameters.isEmpty()) {
			parameters = parameters.substring(0, parameters.length()-1);
		}
		return  super.HTTPS_ECONOMIA_AWESOMEAPI_COM_BR_LAST + parameters ;
	}

	
}
