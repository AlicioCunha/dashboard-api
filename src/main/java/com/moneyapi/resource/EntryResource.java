package com.moneyapi.resource;


import com.moneyapi.model.Entry;
import com.moneyapi.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<Entry> buscarPeloCodigo(@PathVariable Long id) {
        Entry lancamento = entryRepository.findOne(id);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

}
