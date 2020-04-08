package com.moneyapi.service;

import com.moneyapi.model.Entry;
import com.moneyapi.model.Person;
import com.moneyapi.repository.EntryRepository;
import com.moneyapi.repository.PersonRepository;
import com.moneyapi.service.exception.PesonRecordInactiveOrNonexistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntryRepository entryRepository;

    public Entry saveEntry(Entry entry) {
        Person searchedPerson = personRepository.findOne(entry.getPerson().getId());

        if (searchedPerson == null || searchedPerson.isInativo()) {
            throw new PesonRecordInactiveOrNonexistent();
        }
        return entryRepository.save(entry);
    }
}
