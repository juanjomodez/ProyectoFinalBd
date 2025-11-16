package com.proyecto.backend_club_de_lectura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;

@Repository //le dice al springboot que esta interfaz maneja operaciones con la bd
public interface IRetoLecturaRepository extends JpaRepository<RetoLecturaModel, Integer> {//le decimos que la pk es un int
    // creamos una interfaz y heredamos el JpaRepository que tiene implementado 
    // las operaciones CRUD
}
