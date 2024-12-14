package exchange.resources;

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

import exchange.domain.dto.ExchangeRateDTO;
import exchange.services.ExchangeRateService;

@RestController
@RequestMapping(value = "exchangeRate")
public class ExchangeRateResource {

	@Autowired
	ExchangeRateService exchangeRateService;
	@Autowired
	private ModelMapper mapper;
	private static final String ID = "/{id}";

	@GetMapping(value = ID)
	public ResponseEntity<ExchangeRateDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(exchangeRateService.findById(id), ExchangeRateDTO.class));
	}

	@GetMapping
	public ResponseEntity<List<ExchangeRateDTO>> findAll() {
		return ResponseEntity.ok().body(
				exchangeRateService.findAll().stream().map(x -> mapper.map(x, ExchangeRateDTO.class)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ExchangeRateDTO> create(@RequestBody ExchangeRateDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(exchangeRateService.create(obj).getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}


	@PutMapping("update")
	public ResponseEntity<ExchangeRateDTO> update(@RequestBody ExchangeRateDTO obj) {
		return ResponseEntity.ok().body(mapper.map(exchangeRateService.update(obj), ExchangeRateDTO.class));
	}

	@DeleteMapping(value = ID)
	public ResponseEntity<ExchangeRateDTO> delete(@PathVariable Integer id) {
		exchangeRateService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
