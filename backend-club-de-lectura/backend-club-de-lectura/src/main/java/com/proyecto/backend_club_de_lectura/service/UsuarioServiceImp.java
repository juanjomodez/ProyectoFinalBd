package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.UsuarioModel;
import com.proyecto.backend_club_de_lectura.repository.IUsuarioRepository;
import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioModel obtenerUsuarioPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con ID " + id + " no existe"));
    }

    @Override
    public void eliminarUsuario(int id) {

        usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con ID " + id + " no existe"));

        usuarioRepository.deleteById(id);
    }
}
