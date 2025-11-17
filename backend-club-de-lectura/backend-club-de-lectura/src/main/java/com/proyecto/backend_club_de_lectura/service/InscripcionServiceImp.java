package com.proyecto.backend_club_de_lectura.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.exception.ErrorLogicoException;
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

        // Validar si ya existe inscripción
        if (inscripcionRepository.findByUsuarioIdUsuarioAndRetoIdReto(idUsuario, idReto).isPresent()) {
            throw new ErrorLogicoException("El usuario ya está inscrito en este reto.");
        }

        // Buscar usuario
        UsuarioModel usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException("Usuario con ID " + idUsuario + " no encontrado"));

        // Validar rol
        if (usuario.getRol() != UsuarioModel.Rol.lector) {
            throw new ErrorLogicoException("Solo los usuarios con rol 'lector' pueden inscribirse.");
        }

        // Buscar reto
        RetoLecturaModel reto = retoLecturaRepository.findById(idReto)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException("Reto con ID " + idReto + " no encontrado"));

        // Crear inscripción
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
                .orElseThrow(() ->
                    new RecursoNoEncontradoException("Inscripción con ID " + idInscripcion + " no encontrada"));

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
                .orElseThrow(() ->
                    new RecursoNoEncontradoException("Inscripción con ID " + idInscripcion + " no encontrada"));
    }
}
