package com.moneyapi.resource;

import com.moneyapi.event.ResourceCreatedEvent;
import com.moneyapi.model.Category;
import com.moneyapi.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    //Uma sugestao para um retorno quando no possuir nenhum registro na tabela. o correto é utilizar o metodoo abaixo
    /* *** Exemplo: *** */
    @GetMapping("/listarTudo")
    public ResponseEntity<?> listarTudo() {
        List<Category> categories = categoriesRepository.findAll();
        return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Category> listAll() {
        return categoriesRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category categoryCreated = categoriesRepository.save(category);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, categoryCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> searchForId(@PathVariable Long id) {
        Category searchedCategory = categoriesRepository.findOne(id);
        return searchedCategory != null ? ResponseEntity.ok(searchedCategory) : ResponseEntity.notFound().build();
    }
    
}
