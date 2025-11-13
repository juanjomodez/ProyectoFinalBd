package com.proyecto.backend_club_de_lectura.service; // el archivo pertenece a este paquete

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.UsuarioModel;

public interface IUsuarioService {

    // listar todos los usuarios, muestra todos los usuarios de la bd
    List<UsuarioModel> listarUsuarios();

    // guardar un usuario, crea o actualiza un usuario
    UsuarioModel guardarUsuario(UsuarioModel usuario);

    // buscar un usuario por ID
    UsuarioModel obtenerUsuarioPorId(int id);

    // borrar un usuario por ID
    void eliminarUsuario(int id);
}
