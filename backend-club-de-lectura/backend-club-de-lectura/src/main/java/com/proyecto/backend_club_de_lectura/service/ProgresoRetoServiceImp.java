package com.proyecto.backend_club_de_lectura.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.exception.ErrorLogicoException;
import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.ILibroRepository;
import com.proyecto.backend_club_de_lectura.repository.ILibrosRetoRepository;
import com.proyecto.backend_club_de_lectura.repository.IProgresoRetoRepository;

@Service
public class ProgresoRetoServiceImp implements IProgresoRetoService {
    
    @Autowired
    private IProgresoRetoRepository progresoRepo;

    @Autowired
    private IInscripcionRepository inscripcionRepo;

    @Autowired
    private ILibrosRetoRepository librosRetoRepo;

    @Autowired
    private ILibroRepository libroRepo;

    @Override
    public ProgresoRetoModel registrarProgreso(Integer idInscripcion, Integer idLibro, Double porcentaje) {
        
        var inscripcion = inscripcionRepo.findById(idInscripcion)
            .orElseThrow(() -> new RecursoNoEncontradoException("Inscripción no encontrada"));

        var libro = libroRepo.findById(idLibro)
            .orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado"));

        boolean pertenece = librosRetoRepo.existsByLibro_IdLibroAndReto_IdReto(
                idLibro, 
                inscripcion.getReto().getIdReto()
        );

        if (!pertenece) {
            throw new ErrorLogicoException("El libro no pertenece a este reto");
        }

        if (progresoRepo.existsByInscripcion_IdInscripcionAndLibro_IdLibro(idInscripcion, idLibro)) {
            throw new ErrorLogicoException("Ya existe un progreso registrado para este libro");
        }

        ProgresoRetoModel pr = new ProgresoRetoModel();
        pr.setInscripcion(inscripcion);
        pr.setLibro(libro);
        pr.setPorcentajeAvance(porcentaje);
        pr.setFechaActualizacion(new Date());
        pr.setEstado(ProgresoRetoModel.Estado.en_progreso);

        return progresoRepo.save(pr);
    }

    @Override
    public ProgresoRetoModel actualizarProgreso(Integer idProgreso, Double nuevoPorcentaje) {
        
        var progreso = progresoRepo.findById(idProgreso)
            .orElseThrow(() -> new RecursoNoEncontradoException("Progreso no encontrado"));

        if (nuevoPorcentaje < 0 || nuevoPorcentaje > 100) {
            throw new ErrorLogicoException("El porcentaje debe estar entre 0 y 100");
        }

        progreso.setPorcentajeAvance(nuevoPorcentaje);
        progreso.setFechaActualizacion(new Date());

        if (nuevoPorcentaje >= 100) {
            progreso.setEstado(ProgresoRetoModel.Estado.completado);
        } else {
            progreso.setEstado(ProgresoRetoModel.Estado.en_progreso);
        }

        return progresoRepo.save(progreso);
    }

    @Override
    public List<ProgresoRetoModel> obtenerProgresoDeInscripcion(Integer idInscripcion) {
        return progresoRepo.findByInscripcion_IdInscripcion(idInscripcion);
    }

    @Override
    public ProgresoRetoModel obtenerProgresoPorId(Integer idProgreso) {
        return progresoRepo.findById(idProgreso)
            .orElseThrow(() -> new RecursoNoEncontradoException("Progreso no encontrado"));
    }
}