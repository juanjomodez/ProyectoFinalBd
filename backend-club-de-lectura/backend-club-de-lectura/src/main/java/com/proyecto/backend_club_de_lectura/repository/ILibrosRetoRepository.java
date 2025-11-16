package com.proyecto.backend_club_de_lectura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_club_de_lectura.model.LibrosRetoModel;

@Repository
public interface ILibrosRetoRepository extends JpaRepository<LibrosRetoModel, Integer> {
    // Verifica si un libro pertenece a un reto
    boolean existsByLibro_IdLibroAndReto_IdReto(Integer idLibro, Integer idReto);
}
