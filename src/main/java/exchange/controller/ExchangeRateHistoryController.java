package exchange.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import exchange.domain.ExchangeRate;
import exchange.domain.dto.ExchangeRateDTO;
import exchange.domain.dto.ExchangeRateHistoryDTO;
import exchange.services.ExchangeRateHistoryService;
import exchange.services.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "exchangeRateHistory")
public class ExchangeRateHistoryController {

	@Autowired
	ExchangeRateHistoryService exchangeRateHistoryService;
	
	@Autowired
	private ModelMapper mapper;
	private static final String ID = "/{id}";

	@Operation(summary = "Retrieve a ExchangeRateHistory by Id of ExchangeRate", description = "Get a List of ExchangeRateHistory  by specifying its id ExchangeRate. The response is List ExchangeRateHistory.", tags = {
			"exchangeRateHistory", "get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = ID)
	
	public ResponseEntity<List<ExchangeRateHistoryDTO>> findAllHistoryByIdExchangeRate(@PathVariable Integer id) {
		System.out.println("Start");
		return ResponseEntity.ok().body(
				exchangeRateHistoryService.findAllHistoryByIdExchangeRate(id).stream().map(x -> mapper.map(x, ExchangeRateHistoryDTO.class)).collect(Collectors.toList()));
	}

	
	

}
