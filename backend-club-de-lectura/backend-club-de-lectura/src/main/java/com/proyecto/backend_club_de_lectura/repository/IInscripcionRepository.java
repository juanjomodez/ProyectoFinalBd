package com.proyecto.backend_club_de_lectura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;

@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Integer> {
    int countByReto_IdReto(int idReto);
    // Para validar si ya existe inscripción
    Optional<InscripcionModel> findByUsuarioIdUsuarioAndRetoIdReto(int idUsuario, int idReto);

    // Para listar inscripciones de un usuario
    List<InscripcionModel> findByUsuarioIdUsuario(int idUsuario);
}

