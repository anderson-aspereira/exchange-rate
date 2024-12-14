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

import exchange.domain.dto.CompanyDTO;
import exchange.services.CompanyService;

@RestController
@RequestMapping(value = "company")
public class CompanyResource {

	@Autowired
	CompanyService companyService;
	@Autowired
	private ModelMapper mapper;
	private static final String ID = "/{id}";

	@GetMapping(value = ID)
	public ResponseEntity<CompanyDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(companyService.findById(id), CompanyDTO.class));
	}

	@GetMapping
	public ResponseEntity<List<CompanyDTO>> findAll() {
		return ResponseEntity.ok().body(
				companyService.findAll().stream().map(x -> mapper.map(x, CompanyDTO.class)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(companyService.create(obj).getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}


	@PutMapping("update")
	public ResponseEntity<CompanyDTO> update(@RequestBody CompanyDTO obj) {
		return ResponseEntity.ok().body(mapper.map(companyService.update(obj), CompanyDTO.class));
	}

	@DeleteMapping(value = ID)
	public ResponseEntity<CompanyDTO> delete(@PathVariable Integer id) {
		companyService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
