package com.proyecto.backend_club_de_lectura.service; // el archivo pertenece a este paquete

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.UsuarioModel;

public interface IUsuarioService {

    // listamos todos los usuarios, muestra todos los usuarios de la bd
    List<UsuarioModel> listarUsuarios();

    // con esto guardamos un usuario, crea o actualiza un usuario
    UsuarioModel guardarUsuario(UsuarioModel usuario);

    // sirve para buscar un usuario por su id
    UsuarioModel obtenerUsuarioPorId(int id);

    // permite borrar un usuario por id
    void eliminarUsuario(int id);
}
