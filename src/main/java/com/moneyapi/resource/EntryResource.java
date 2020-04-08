package com.moneyapi.resource;


import com.moneyapi.model.Entry;
import com.moneyapi.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryResource {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public List<Entry> listAll() {
        return entryRepository.findAll();
    }
}
