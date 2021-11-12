package com.devsuperior.bds02.service;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exception.DscatalogNotfoundException;
import com.devsuperior.bds02.repository.CityRespository;
import com.devsuperior.bds02.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private CityRespository cityRepository;

	@Transactional
	public EventDTO updateEvent(Long id, EventDTO event) {

		try {

			Event responseEvent = repository.getOne(id);

			copyDtoToEntity(event, responseEvent);

			responseEvent = repository.save(responseEvent);

			return new EventDTO(responseEvent);

		} catch (EntityNotFoundException e) {
			throw new DscatalogNotfoundException("Não conseguiu atualizar a Evento");
		}

	}

	private void copyDtoToEntity(EventDTO event, Event responseEvent) {
		responseEvent.setName(event.getName());
		responseEvent.setUrl(event.getUrl());
		responseEvent.setDate(event.getDate());
		City city = cityRepository.getOne(event.getCityId());
		responseEvent.setCity(city);
	}

}
