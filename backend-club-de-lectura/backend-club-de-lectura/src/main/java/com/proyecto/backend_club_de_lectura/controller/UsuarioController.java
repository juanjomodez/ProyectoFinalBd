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

@RestController 
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    // listar todos los usuarios
    @GetMapping
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // obtener usuario por ID
    @GetMapping("/obtenerUsuario/{id}") //colocamos variables de ruta, nos sirve para identificar el recurso especifico a trabajar
    public UsuarioModel obtenerUsuario(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // crear un usuario
    @PostMapping("/insertar") // no tenemos path variable ya que aca creamos una variable no existente, las variables de ruta se utilizan solo para variables existentes
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // actualizar usuario SIN setIdUsuario
    @PutMapping("/actualizar/{id}")
public UsuarioModel actualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuarioActualizado) {
    UsuarioModel usuarioExistente = usuarioService.obtenerUsuarioPorId(id);

    usuarioExistente.setNombreCompleto(usuarioActualizado.getNombreCompleto());
    usuarioExistente.setEdad(usuarioActualizado.getEdad());
    usuarioExistente.setOcupacion(usuarioActualizado.getOcupacion());
    usuarioExistente.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
    usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
    usuarioExistente.setRol(usuarioActualizado.getRol());

    return usuarioService.guardarUsuario(usuarioExistente);
}


    // eliminar usuario por ID
    @DeleteMapping("/eliminarUsuario/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }
}
