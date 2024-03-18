package com.devsuperior.demo.resources;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {
    @Autowired
    private CityService cityservice;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){

        List<CityDTO>list= cityservice.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
        dto= cityservice.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value= "/{id}")
    public ResponseEntity<CityDTO> delete (@PathVariable Long id){
        cityservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
