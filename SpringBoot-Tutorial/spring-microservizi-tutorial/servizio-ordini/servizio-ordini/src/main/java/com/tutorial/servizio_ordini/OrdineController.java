package com.tutorial.servizio_ordini;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    // GET /ordini
    @GetMapping
    public List<Ordine> getOrdini() {
        return ordineService.getOrdini();
    }

    // GET /ordini/1
    @GetMapping("/{id}")
    public Ordine getOrdineById(@PathVariable Long id) {
        return ordineService.getOrdineById(id);
    }

    // GET /ordini/utente/1
    @GetMapping("/utente/{utenteId}")
    public List<Ordine> getOrdiniByUtente(@PathVariable Long utenteId) {
        return ordineService.getOrdiniByUtente(utenteId);
    }

    // POST /ordini
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ordine creaOrdine(@Valid @RequestBody Ordine ordine) {
        return ordineService.creaOrdine(ordine);
    }

    // DELETE /ordini/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaOrdine(@PathVariable Long id) {
        ordineService.eliminaOrdine(id);
    }
}