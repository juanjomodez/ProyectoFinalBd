package com.proyecto.backend_club_de_lectura.service;

import java.util.List;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;

public interface IRetoLecturaService {

    List<RetoLecturaModel> listarRetos();

    RetoLecturaModel guardarReto(RetoLecturaModel reto);

    RetoLecturaModel obtenerRetoPorId(int id);

    RetoLecturaModel actualizarReto(int id, RetoLecturaModel datos);

    void eliminarReto(int id) throws Exception;
}
