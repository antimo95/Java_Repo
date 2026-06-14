package com.tutorial.servizio_ordini;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// "servizio-utenti" è il nome con cui è registrato su Eureka
// Feign chiede a Eureka l'indirizzo e fa la chiamata automaticamente

@FeignClient(name = "servizio-utenti")
//@FeignClient(name = "servizio-utenti", url = "http://localhost:8081")
//La 2° indicazione genererebbe un caso di Hardcoded endpoint
public interface UtenteClient {

    @GetMapping("/utenti/{id}")
    UtenteResponse getUtenteById(@PathVariable Long id);
}
