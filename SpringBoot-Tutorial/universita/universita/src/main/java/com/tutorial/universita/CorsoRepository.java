package com.tutorial.universita;

import org.springframework.data.jpa.repository.JpaRepository;


//Ho accesso ai metodi di default di JPA estendendola
public interface CorsoRepository extends JpaRepository<Corso, Long> {
}
