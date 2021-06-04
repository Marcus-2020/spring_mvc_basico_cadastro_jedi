package com.marstechnologiesbr.springwebmvc.rest;

import com.marstechnologiesbr.springwebmvc.exception.JediNotFoundException;
import com.marstechnologiesbr.springwebmvc.model.Jedi;
import com.marstechnologiesbr.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JediResource {

    @Autowired
    private JediService service;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){
        return service.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") final Long id) {
        final Jedi jedi;
        try {
            jedi = service.findById(id);
            return ResponseEntity.ok(jedi);
        } catch (JediNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
