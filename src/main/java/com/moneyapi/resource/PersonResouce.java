package com.moneyapi.resource;

import com.moneyapi.model.Person;
import com.moneyapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Person> searchForId(@PathVariable Long id){
        Person seachedPerson = personRepository.findOne(id);
        return seachedPerson != null ? ResponseEntity.ok(seachedPerson) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void create(@RequestBody Person person){
        personRepository.save(person);
    }
}
