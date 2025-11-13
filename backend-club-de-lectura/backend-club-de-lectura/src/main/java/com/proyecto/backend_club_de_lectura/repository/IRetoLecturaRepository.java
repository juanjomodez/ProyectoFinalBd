package com.proyecto.backend_club_de_lectura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import org.springframework.stereotype.Repository;

@Repository
public interface IRetoLecturaRepository extends JpaRepository<RetoLecturaModel, Integer> {
}
