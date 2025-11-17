package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.exception.RetoConInscritosException;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;

@Service
public class RetoLecturaServiceImp implements IRetoLecturaService {

    @Autowired
    private IRetoLecturaRepository retoRepository;

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Override
    public List<RetoLecturaModel> listarRetos() {
        return retoRepository.findAll();
    }

    @Override
    public RetoLecturaModel guardarReto(RetoLecturaModel reto) {
        return retoRepository.save(reto);
    }

    @Override
    public RetoLecturaModel obtenerRetoPorId(int id) {
        return retoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El reto con ID " + id + " no existe"));
    }

    @Override
    public RetoLecturaModel actualizarReto(int id, RetoLecturaModel datos) {
        RetoLecturaModel existente = retoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se puede actualizar. El reto con ID " + id + " no existe"));

        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());

        return retoRepository.save(existente);
    }

    @Override
    public void eliminarReto(int id) {

        // Validamos que exista
        RetoLecturaModel reto = retoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se puede eliminar. El reto con ID " + id + " no existe"));

        // Verificamos si tiene inscripciones
        int inscritos = inscripcionRepository.countByReto_IdReto(id);

        if (inscritos > 0) {
            throw new RetoConInscritosException(
                    "No se puede eliminar el reto: tiene " + inscritos + " usuarios inscritos");
        }

        // Si pasa todas las validaciones
        retoRepository.deleteById(id);
    }
}
