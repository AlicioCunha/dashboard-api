package com.moneyapi.resource;

import com.moneyapi.model.Category;
import com.moneyapi.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoriesRepository categoriesRepository;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Category category){
        categoriesRepository.save(category);
    }


}
