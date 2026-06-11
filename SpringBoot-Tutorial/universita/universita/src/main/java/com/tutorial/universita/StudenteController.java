package com.tutorial.universita;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studenti")
public class StudenteController {

    public final StudenteService studenteService;


    public StudenteController(StudenteService studenteService) {
        this.studenteService = studenteService;
    }


    // GET /studenti
    @GetMapping
    public List<Studente> getStudenti() {
        return studenteService.GetStudenti();
    }

    // GET /studenti/1
    @GetMapping("/{id}")
    public Studente getStudenteById(@PathVariable Long id) {
        return studenteService.GetStudenteById(id);
    }

    // POST /studenti?corsoId=1
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Studente aggiungiStudente(@RequestParam Long corsoId,
                                     @Valid @RequestBody Studente studente) {
        return studenteService.aggiungiStudente(corsoId, studente);
    }

    // PUT /studenti/1/corso?corsoId=2
    @PutMapping("/{id}/corso")
    public Studente cambiaCorso(@PathVariable Long id,
                                @RequestParam Long corsoId) {
        return studenteService.cambiaCorso(id, corsoId);
    }

    // DELETE /studenti/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaStudente(@PathVariable Long id) {
        studenteService.eliminaStudente(id);
    }
}
