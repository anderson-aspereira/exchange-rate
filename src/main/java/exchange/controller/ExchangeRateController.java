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
import exchange.services.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "exchangeRate")
public class ExchangeRateController {

	@Autowired
	ExchangeRateService exchangeRateService;
	
	@Autowired
	private ModelMapper mapper;
	private static final String ID = "/{id}";

	@Operation(summary = "Retrieve a ExchangeRate by Id", description = "Get a ExchangeRate object by specifying its id. The response is ExchangeRate object with id, code, codeIn, name, currentValue  and date.", tags = {
			"exchangeRate", "get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = ID)
	public ResponseEntity<ExchangeRateDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(exchangeRateService.findById(id), ExchangeRateDTO.class));
	}

	@Operation(summary = "Retrieve all ExchangeRate", tags = { "exchangeRate", "get", "filter" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping
	public ResponseEntity<List<ExchangeRateDTO>> findAll() {
		System.out.println("Start");
		return ResponseEntity.ok().body(
				exchangeRateService.findAll().stream().map(x -> mapper.map(x, ExchangeRateDTO.class)).collect(Collectors.toList()));
	}

	 @Operation(summary = "Create a new ExchangeRate", tags = { "exchangeRate", "post" })
	  @ApiResponses({
	      @ApiResponse(responseCode = "201", content = {
	          @Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping
	public ResponseEntity<ExchangeRateDTO> create(@RequestBody ExchangeRateDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(exchangeRateService.create(obj).getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}


	 @Operation(summary = "Update a ExchangeRate by Id", tags = { "exchangeRate", "put" })
	  @ApiResponses({
	      @ApiResponse(responseCode = "200", content = {
	          @Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("update")
	public ResponseEntity<ExchangeRateDTO> update(@RequestBody ExchangeRateDTO obj) {
		return ResponseEntity.ok().body(mapper.map(exchangeRateService.update(obj), ExchangeRateDTO.class));
	}

	 @Operation(summary = "Delete a ExchangeRate by Id", tags = { "exchangeRate", "delete" })
	  @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	
	@DeleteMapping(value = ID)
	public ResponseEntity<ExchangeRateDTO> delete(@PathVariable Integer id) {
		exchangeRateService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	 
	 @Operation(summary = "Update all ExchangeRate ", tags = { "exchangeRate", "put" })
	  @ApiResponses({
	      @ApiResponse(responseCode = "200", content = {
	          @Content(schema = @Schema(implementation = ExchangeRate.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("updateAll")
	public ResponseEntity<ExchangeRateDTO> updateAll() {
		 exchangeRateService.updateAll();
		return ResponseEntity.ok().build();
	}

}
