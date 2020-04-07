package com.moneyapi.resource;

import com.moneyapi.event.ResourceCreatedEvent;
import com.moneyapi.model.Person;
import com.moneyapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonResouce {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

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
        publisher.publishEvent(new ResourceCreatedEvent(this, response, personCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        personRepository.delete(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
        Person searchedPerson = personRepository.findOne(id);
        BeanUtils.copyProperties(person, searchedPerson, "id");
        personRepository.save(searchedPerson);
        return ResponseEntity.ok(searchedPerson);
    }
}
