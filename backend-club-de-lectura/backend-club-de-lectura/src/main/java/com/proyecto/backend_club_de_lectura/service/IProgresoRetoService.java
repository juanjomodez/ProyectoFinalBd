package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;

public interface IProgresoRetoService {
// Registrar el primer progreso (si no existe aún)
    ProgresoRetoModel registrarProgreso(Integer idInscripcion, Integer idLibro, Double porcentaje);

    // Actualizar el progreso existente
    ProgresoRetoModel actualizarProgreso(Integer idProgreso, Double nuevoPorcentaje);

    // Obtener progreso por inscripción (un usuario dentro de un reto)
    List<ProgresoRetoModel> obtenerProgresoDeInscripcion(Integer idInscripcion);

    // Obtener progreso específico
    ProgresoRetoModel obtenerProgresoPorId(Integer idProgreso);
}
