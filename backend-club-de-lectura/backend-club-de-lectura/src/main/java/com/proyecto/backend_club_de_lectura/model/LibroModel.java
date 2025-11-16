package com.proyecto.backend_club_de_lectura.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //le indica a spring boot que eso representa una tabla
@Table(name = "libro") //le especifica al sistema el nombre de la tabla en mysql
@Data //genera getters y setters automaticamente
@AllArgsConstructor //genera constructor con todos los atributos
@NoArgsConstructor //genera constructor sin atributos

public class LibroModel {
    @Id // lo define como una pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es para que el valor de id se 
    // coloque automaticamente
    private Integer idLibro;
    private String titulo;
    private String autor;
    private String genero;
    private Integer anoPublicacion;
    private String sinopsis;
    private String portada;

    @Temporal(TemporalType.DATE) // indica que solo se guarde la fecha sin la hora
    private Date fechaSeleccion;

    @Enumerated(EnumType.STRING) //se usa para guardar EstadoLibro como string ya que lo tenemos como un enum, para no tener problemas con posibles cambios de orden
    private EstadoLectura estado;
    public enum EstadoLectura {
        pendiente,
        en_lectura,
        leido
    }
}
