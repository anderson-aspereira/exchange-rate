package exchange.services.externals;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import exchange.config.RestTemplateClient;

@Service
public class ApiService {
    private final RestTemplateClient restTemplateClient;


    public ApiService(RestTemplateClient restTemplateClient) {
        this.restTemplateClient = restTemplateClient;
       
    }

    public String callExternalApi( String url) {
        
        ResponseEntity<String> response = restTemplateClient.restTemplate().getForEntity(url, String.class);
        return response.getBody();
    }
}