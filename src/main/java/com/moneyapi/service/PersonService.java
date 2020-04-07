package com.moneyapi.service;

import com.moneyapi.model.Person;
import com.moneyapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void updateRecordStatus(Long id, Boolean active) {
        Person searchedPerson = findPersonById(id);
        searchedPerson.setActive(active);
        personRepository.save(searchedPerson);
    }

    public Person updateService(Long id, Person person) {
        Person searchedPerson = findPersonById(id);
        BeanUtils.copyProperties(person, searchedPerson, "id");
        return personRepository.save(searchedPerson);
    }

    private Person findPersonById(Long id) {
        Person searchedPerson = personRepository.findOne(id);
        if (searchedPerson == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return searchedPerson;
    }
}
