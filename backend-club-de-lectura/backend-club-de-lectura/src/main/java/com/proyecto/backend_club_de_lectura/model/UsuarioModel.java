package com.proyecto.backend_club_de_lectura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity //le indica a spring boot que eso representa una tabla
@Table(name = "usuario") //le especifica al sistema el nombre de la tabla en mysql
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    @Id // lo define como una pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es para que el valor de id se 
    // coloque automaticamente
    private int idUsuario;

    private String nombreCompleto;
    private int edad;
    private String ocupacion;
    private String correoElectronico;
    private int telefono;

    @Enumerated(EnumType.STRING) //se usa para guardar Rol como string
    // ya que lo tenemos como un enum, para no tener problemas con posibles cambios de orden despues/
    private Rol rol;

    public enum Rol {
        lector,
        moderador,
        administrador
    }

    // hacemos getters y setters para poder acceder a los valores
    // get es para leer un valor privado y set es para modificar un valor pirvado
    
}
