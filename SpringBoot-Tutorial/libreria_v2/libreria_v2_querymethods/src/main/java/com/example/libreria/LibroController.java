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

    // GET /libri/cerca?titolo=rosa
    @GetMapping("/cerca")
    public List<Libro> cercaPerTitolo(@RequestParam String titolo){
        return libroservice.cercaPerTitolo(titolo);
    }

    // GET /libri/dopo?anno=1980
    @GetMapping("/dopo")
    public List<Libro> cercaPerAnno(@RequestParam int anno){
        return libroservice.cercaPerAnno(anno);
    }

    // GET /libri/autore/1
    @GetMapping("/autore/{autoreId}")
    public List<Libro> cercaPerAutore(@PathVariable Long autoreId) {
        return libroservice.cercaPerAutore(autoreId);
    }

    // GET /libri/cognome?cognome=Eco
    @GetMapping("/cognome")
    public List<Libro> cercaPerCognomeAutore(@RequestParam String cognome) {
        return libroservice.cercaPerCognomeAutore(cognome);
    }

    // GET /libri/filtra?anno=1900&parola=rosa
    @GetMapping("/filtra")
    public List<Libro> filtra(@RequestParam int anno,
                              @RequestParam String parola) {
        return libroservice.cercaPerAnnoEParola(anno, parola);
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
