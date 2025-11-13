package com.proyecto.backend_club_de_lectura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;

@Service
public class RetoLecturaServiceImp implements IRetoLecturaService {

    @Autowired
    private IRetoLecturaRepository retoRepository;

    @Autowired(required = false) // se pone el false para decirle al sistema que falle si aun no existe el bean
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
        Optional<RetoLecturaModel> opt = retoRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void eliminarReto(int id) {
        if (inscripcionRepository != null) {
            int count = inscripcionRepository.countByReto_IdReto(id);
            if (count > 0) {
                throw new IllegalStateException("No se puede eliminar el reto porque tiene inscritos.");
            }
        }
        retoRepository.deleteById(id);
    }
}

