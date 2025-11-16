package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;

public interface IInscripcionService {
    InscripcionModel inscribirUsuarioEnReto(int idUsuario, int idReto);

    InscripcionModel cancelarInscripcion(int idInscripcion);

    List<InscripcionModel> obtenerInscripcionesPorUsuario(int idUsuario);

    public InscripcionModel buscarPorId(int idInscripcion);


}
