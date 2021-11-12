package com.devsuperior.bds02.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exception.DataBaseException;
import com.devsuperior.bds02.exception.DscatalogNotfoundException;
import com.devsuperior.bds02.repository.CityRespository;

@Service
public class CityService {

	@Autowired
	private CityRespository repository;

	public List<CityDTO> findAllCity() {

		List<City> lisCity = repository.findAll(Sort.by("name"));

		return lisCity.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public CityDTO saveCity(CityDTO dto) {

		City city = new City(dto.getId(), dto.getName());

		City responsecCity = repository.save(city);

		return new CityDTO(responsecCity);
	}

	public void deleteCity(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new DscatalogNotfoundException("Não conseguiu deletar ");

		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("violação de integridade");
		}

	}

}
