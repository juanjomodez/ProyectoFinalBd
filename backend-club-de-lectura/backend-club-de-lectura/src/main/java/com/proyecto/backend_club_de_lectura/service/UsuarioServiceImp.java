package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.UsuarioModel;
import com.proyecto.backend_club_de_lectura.repository.IUsuarioRepository;

@Service // le dice a Spring Bot que esta clase es un "servicio"
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired // le dice al sistema que conectaremos esta clase con otra
    private IUsuarioRepository usuarioRepository;

    // metodo para listar todos los usuarios
    @Override
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // metodo para guardar o actualizar un usuario
    @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    // metodo para obtener un usuario por su id
    @Override
    public UsuarioModel obtenerUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(null); //si no lo encuentra es null
    }

    // metodo para eliminar un usuario por su id, el void no devuelve valores
    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
 