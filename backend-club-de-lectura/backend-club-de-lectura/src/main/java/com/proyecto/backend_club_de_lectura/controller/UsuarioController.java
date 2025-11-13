package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.UsuarioModel;
import com.proyecto.backend_club_de_lectura.service.IUsuarioService;

@RestController // indica que la clase manejara peticiones de API rest
@RequestMapping("/api/usuarios") // ruta base del endpoint
public class UsuarioController {

    @Autowired //conecta con la capa de service que creamos
    private IUsuarioService usuarioService;

    // listar todos los usuarios
    @GetMapping // indica get para obtener datos
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // obtener un usuario por id
    @GetMapping("/{id}") //indica get para listar un usuario, se usa id ya que ya existe en el sistema
    public UsuarioModel obtenerUsuario(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // crear un nuevo usuario
    @PostMapping // indica post para crear un usuario, no usamos id pq aun no existe
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // actualizar un usuario existente
    @PutMapping("/{id}") //indica put para actualizar un usuario
    public UsuarioModel actualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        usuario.setIdUsuario(id);
        return usuarioService.guardarUsuario(usuario);
    }

    // eliminar un usuario por id
    @DeleteMapping("/{id}") // indica delete para borrar el usuario
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }
}
