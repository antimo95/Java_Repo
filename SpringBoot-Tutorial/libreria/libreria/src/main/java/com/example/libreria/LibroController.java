package com.example.libreria;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libri")
public class LibroController {

    private final LibroService libroservice;

    public LibroController(LibroService libroservice) {
        this.libroservice = libroservice;
    }

    @GetMapping
    public List<Libro> getLibri(){
        return libroservice.getLibri();
    }

    @GetMapping("/{id}")
    public Libro getById(@PathVariable long id){
        return libroservice.getById(id);
    }

    // AutoreID arriva come parametro nell'url non nel body
    // RequestParam estrae il parametro dopo ? nell'URL
    // /libri?autoreId = 1 => prende 1
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro aggiungiLibro(@RequestParam long autoreId,
                               @Valid @RequestBody Libro libro){
        return libroservice.aggiungiLibro(autoreId, libro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaLibro(long id){
        libroservice.eliminaLibro(id);
    }
}
