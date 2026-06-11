package com.tutorial.universita;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    private final CorsoService service;

    public CorsoController(CorsoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Corso> getCorsi(){
        return service.getCorsi();
    }

    @GetMapping("/{id}")
    public Corso getCorsoId(@PathVariable Long id){
        return service.getCorsoId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Corso aggiungiCorso(@Valid @RequestBody Corso corso){
        return service.aggiungiCorso(corso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaCorso(@PathVariable Long id){
        service.eliminaCorso(id);
    }
}






