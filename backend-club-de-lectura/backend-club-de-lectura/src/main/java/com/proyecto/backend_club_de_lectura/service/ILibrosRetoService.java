package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.LibrosRetoModel;

public interface ILibrosRetoService {

    LibrosRetoModel agregarLibroAReto(Integer idLibro, Integer idReto);

    List<LibrosRetoModel> obtenerLibrosPorReto(Integer idReto);

    void eliminarLibroDeReto(Integer idLibrosReto);
}
