package com.moneyapi.resource;


import com.moneyapi.event.ResourceCreatedEvent;
import com.moneyapi.model.Entry;
import com.moneyapi.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryResource {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Entry> listAll() {
        return entryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> buscarPeloCodigo(@PathVariable Long id) {
        Entry lancamento = entryRepository.findOne(id);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entryCreated = entryRepository.save(entry);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, entryCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entryCreated);
    }

}
