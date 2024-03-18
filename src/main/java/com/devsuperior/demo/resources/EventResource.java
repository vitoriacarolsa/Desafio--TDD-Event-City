package com.devsuperior.demo.resources;


import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventResource {
    @Autowired
    private EventService eventService;

    @PutMapping(value= "/{id}")
    public ResponseEntity<EventDTO> update (@PathVariable Long id, @RequestBody EventDTO dto){
        dto= eventService.update(id, dto, dto.getCityId());
        return ResponseEntity.ok().body(dto);
    }

}
