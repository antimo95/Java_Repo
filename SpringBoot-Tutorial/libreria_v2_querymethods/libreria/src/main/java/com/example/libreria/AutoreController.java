package com.example.libreria;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreservice){
        this.autoreService = autoreservice;
    }

    @GetMapping
    public List<Autore> getAutori(){
        return autoreService.getAutori();
    }

    @GetMapping("/{id}")
    public Autore getAutoreById(@PathVariable Long id){
        return autoreService.getAutoreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore aggiungiAutore(@Valid @RequestBody Autore autore){
        return autoreService.aggiungiAutore(autore);
    }

    @PutMapping("/{id}")
    public Autore aggiornaAutore(@PathVariable long id,
                                 @Valid @RequestBody Autore autoreAggiornato){
        return autoreService.aggiornaAutore(id, autoreAggiornato);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaAutore(@PathVariable Long id){
        autoreService.eliminaAutore(id);
    }
}
