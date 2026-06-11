package com.tutorial.universita;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudenteRepository extends JpaRepository<Studente, Long> {

    // tutti gli studenti iscritti a un corso
    List<Studente> findByCorsoId(Long corsoId);
}
