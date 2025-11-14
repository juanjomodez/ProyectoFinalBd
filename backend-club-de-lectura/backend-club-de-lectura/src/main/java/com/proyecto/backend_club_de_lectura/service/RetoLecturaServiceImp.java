package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;

@Service 
public class RetoLecturaServiceImp implements IRetoLecturaService {

    @Autowired
    private IRetoLecturaRepository retoRepository; 
    // implementamos la interfaz para acceder a los métodos CRUD de la tabla reto

    @Autowired 
    private IInscripcionRepository inscripcionRepository;
    // conectamos con inscripción para validar que un reto no tenga inscritos

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

    // obtener el reto por el id especifico SIN OPTIONAL
    @Override
    public RetoLecturaModel obtenerRetoPorId(int id) {
        // lo que hace es buscar en la bd si el id existe
        // si no existe nos da null
        // usamos el orElse(null) para decir "en otro caso devuelva null"
        return retoRepository.findById(id).orElse(null);
    }

    // permite eliminar retos solo si no tienen inscritos
    @Override
    public void eliminarReto(int id) {

        // contamos cuántas inscripciones tiene ese reto
        int count = inscripcionRepository.countByReto_IdReto(id);

        // si tiene el reto tiene inscritos no se elimina
        if (count > 0) {
            System.out.println("No se puede eliminar el reto porque tiene inscritos.");
            return; 
        }

        // en cambio si no tiene inscritos si se eliminara
        retoRepository.deleteById(id);
    }
}
