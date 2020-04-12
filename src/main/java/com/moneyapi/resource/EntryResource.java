package com.moneyapi.resource;


import com.moneyapi.event.ResourceCreatedEvent;
import com.moneyapi.exceptionhandler.MoneyExceptionHandler;
import com.moneyapi.model.Entry;
import com.moneyapi.repository.EntryRepository;
import com.moneyapi.repository.filter.EntryFilter;
import com.moneyapi.service.EntryService;
import com.moneyapi.service.exception.PesonRecordInactiveOrNonexistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryResource {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EntryService entryService;

    @Autowired
    private MessageSource messageSource;

    /*
    Primeiro metodo de listagem sem filtro
    @GetMapping
    public List<Entry> listAll() {
        return entryRepository.findAll();
    }*/

    // Metodos com filtro customizado
    @GetMapping
    public List<Entry> listAllMetaModel(EntryFilter entryFilter) {
        return entryRepository.filter(entryFilter);
    }

    /*
    //Pesquisa paginada
    @GetMapping
    public Page<Entry> listAllMetaModel(EntryFilter entryFilter, Pageable pageable) {
        return entryRepository.filter(entryFilter, pageable);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Entry> buscarPeloCodigo(@PathVariable Long id) {
        Entry lancamento = entryRepository.findOne(id);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entryCreated = entryService.saveEntry(entry);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, entryCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entryCreated);
    }

    @ExceptionHandler({PesonRecordInactiveOrNonexistent.class})
    public ResponseEntity<Object> handlePesonRecordInactiveOrNonexistent(PesonRecordInactiveOrNonexistent ex) {

        String invalidMessageUser = messageSource.getMessage("person.inativo-ou-inexistente", null, LocaleContextHolder.getLocale());
        String invalidMessageDeveloper = ex.toString();
        List<MoneyExceptionHandler.moneyErrorMessage> errorMessageList = Arrays.asList(new MoneyExceptionHandler.moneyErrorMessage(invalidMessageUser, invalidMessageDeveloper));
        return ResponseEntity.badRequest().body(errorMessageList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        entryRepository.delete(id);
    }

}
