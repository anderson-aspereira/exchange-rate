package exchange.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("${exchange.openapi.dev-url}")
  private String devUrl;



  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

 
   

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Exchange Rate  API")
        .version("1.0")
        .description("This API exposes endpoints to manage exchange rate.")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer));
  }
}
