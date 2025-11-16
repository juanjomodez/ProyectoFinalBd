package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;

public interface IProgresoRetoService {
// registra el primer progreso (si no existe aun)
    ProgresoRetoModel registrarProgreso(Integer idInscripcion, Integer idLibro, Double porcentaje);

    // actualiza el progreso existente
    ProgresoRetoModel actualizarProgreso(Integer idProgreso, Double nuevoPorcentaje);

    // obtiene progreso por inscripción (un usuario dentro de un reto)
    List<ProgresoRetoModel> obtenerProgresoDeInscripcion(Integer idInscripcion);

    // obttiene progreso específico
    ProgresoRetoModel obtenerProgresoPorId(Integer idProgreso);
}
