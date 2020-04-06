package com.moneyapi.resource;

import com.moneyapi.model.Person;
import com.moneyapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonResouce {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> listaAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> searchForId(@PathVariable Long id) {
        Person seachedPerson = personRepository.findOne(id);
        return seachedPerson != null ? ResponseEntity.ok(seachedPerson) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personCreated = personRepository.save(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(personCreated.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(personCreated);
    }
}
