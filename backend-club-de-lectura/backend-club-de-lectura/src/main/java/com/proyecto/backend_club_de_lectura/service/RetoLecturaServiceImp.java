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
    private IRetoLecturaRepository retoRepository; // implementamos la interfaz para acceder a los metodos declarados ahi

    @Autowired(required = false) // se pone el false para decirle al sistema que falle si aun no existe el bean
    private IInscripcionRepository inscripcionRepository;

    // permite listar los retos existentes
    @Override
    public List<RetoLecturaModel> listarRetos() {
        return retoRepository.findAll();
    }


    // guardar el reto
    @Override
    public RetoLecturaModel guardarReto(RetoLecturaModel reto) {
        return retoRepository.save(reto);
    }

    // obtener el reto por el id especifico
    @Override
    public RetoLecturaModel obtenerRetoPorId(int id) { // se usa id por que nos pide obtener el reto por Id especifico
        Optional<RetoLecturaModel> opt = retoRepository.findById(id);
        return opt.orElse(null);
    }

    // permite eliminar retos
    @Override
    public void eliminarReto(int id) {
        if (inscripcionRepository != null) {
            int count = inscripcionRepository.countByReto_IdReto(id);
            if (count > 0) {
                throw new IllegalStateException("No se puede eliminar el reto porque tiene inscritos.");
            }
        }
        retoRepository.deleteById(id); //si el id no existe se borra el reto
    }
}

