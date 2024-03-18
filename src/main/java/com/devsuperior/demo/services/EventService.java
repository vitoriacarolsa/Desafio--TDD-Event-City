package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto, Long cityId) {
        try {
            Event entity = eventRepository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setDate(dto.getDate());
            entity.setUrl(dto.getUrl());

            if (cityId != null) {
                City city = cityRepository.findById(cityId)
                        .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + cityId));
                entity.setCity(city);
            }

            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("id not found" + id);
        }
    }

    }



