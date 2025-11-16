package com.proyecto.backend_club_de_lectura.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.model.UsuarioModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;
import com.proyecto.backend_club_de_lectura.repository.IUsuarioRepository;

@Service
public class InscripcionServiceImp implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRetoLecturaRepository retoLecturaRepository;


    @Override
    public InscripcionModel inscribirUsuarioEnReto(int idUsuario, int idReto) {

        // validar si ya existe
        if (inscripcionRepository.findByUsuarioIdUsuarioAndRetoIdReto(idUsuario, idReto).isPresent()) {
            throw new RuntimeException("El usuario ya está inscrito en este reto.");
        }

        // obtener usuario
        UsuarioModel usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // validar rol
        if (usuario.getRol() != UsuarioModel.Rol.lector) {
            throw new RuntimeException("Solo los usuarios con rol 'lector' pueden inscribirse.");
        }

        // obtener reto
        RetoLecturaModel reto = retoLecturaRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        // crear inscripcion
        InscripcionModel nueva = new InscripcionModel();
        nueva.setUsuario(usuario);
        nueva.setReto(reto);
        nueva.setFecha(new Date());
        nueva.setEstadoInscripcion(InscripcionModel.EstadoInscripcion.activa);

        return inscripcionRepository.save(nueva);
    }

    
    @Override
    public InscripcionModel cancelarInscripcion(int idInscripcion) {
        InscripcionModel inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setEstadoInscripcion(InscripcionModel.EstadoInscripcion.cancelada);

        return inscripcionRepository.save(inscripcion);
    }


    @Override
    public List<InscripcionModel> obtenerInscripcionesPorUsuario(int idUsuario) {
        return inscripcionRepository.findByUsuarioIdUsuario(idUsuario);
    }


    @Override
    public InscripcionModel buscarPorId(int idInscripcion) {
        return inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }
}

