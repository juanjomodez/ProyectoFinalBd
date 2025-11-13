package com.proyecto.backend_club_de_lectura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;

@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Integer> {
    int countByReto_IdReto(int idReto);
}

