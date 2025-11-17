package com.proyecto.backend_club_de_lectura.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "libro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private Integer idLibro;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    private String genero;

    @Column(name = "anoPublicacion")
    private Integer anoPublicacion;

    private String sinopsis;

    private String portada;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaSeleccion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private Date fechaSeleccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoLectura", nullable = false)
    private EstadoLectura estado;

    public enum EstadoLectura {
        Pendiente,
        en_lectura,
        leido
    }
}
