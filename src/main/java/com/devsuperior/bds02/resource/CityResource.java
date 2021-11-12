package com.devsuperior.bds02.resource;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityResource {

	@Autowired
	private CityService service;

	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		return ResponseEntity.ok(service.findAllCity());
	}

	@PostMapping
	public ResponseEntity<CityDTO> save(@RequestBody CityDTO dto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(service.saveCity(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CityDTO> delete(@PathVariable Long id) {

		service.deleteCity(id);
		return ResponseEntity.noContent().build();
	}

}
