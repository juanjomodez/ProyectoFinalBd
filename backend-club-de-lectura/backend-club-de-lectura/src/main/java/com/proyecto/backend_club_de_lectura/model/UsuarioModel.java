package com.proyecto.backend_club_de_lectura.model;

import jakarta.persistence.Column;
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

    @Id // lo define como una primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es para que el valor de id se coloque automaticamente
    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "edad")
    private int edad;

    @Column(name = "ocupacion")
    private String ocupacion;

    @Column(name = "correoElectronico")
    private String correoElectronico;

    @Column(name = "telefono")
    private int telefono;

    @Enumerated(EnumType.STRING) //se usa para guardar Rol como string ya que lo tenemos como un enum, para no tener problemas con posibles cambios de orden despues/
    @Column(name = "rol")
    private Rol rol;

    public enum Rol {
        lector,
        moderador,
        administrador
    }
    
}
