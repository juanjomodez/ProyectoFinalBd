package com.proyecto.backend_club_de_lectura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;

@Repository
public interface IProgresoRetoRepository extends JpaRepository<ProgresoRetoModel, Integer> {
    
boolean existsByInscripcion_IdInscripcionAndLibro_IdLibro(Integer idInscripcion, Integer idLibro);
//revisa si hay un progreso actualmente usando el id de inscripcion y el de libro

List<ProgresoRetoModel> findByInscripcion_IdInscripcion(Integer idInscripcion);
// encuntra los progresos de inscripcion por el id del mismo 
}
