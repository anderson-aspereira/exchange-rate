package exchange.services.externals;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestClientConfig {

	
	private final RestTemplate restTemplate;

    public RestClientConfig() {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    public String getWithBody(Object requestBody) {

        return restTemplate.exchange("https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,BTC-BRL,CAD-BRL", HttpMethod.GET, new HttpEntity<>(requestBody), String.class).getBody();

    }
    
}
