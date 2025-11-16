package com.proyecto.backend_club_de_lectura.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//le indica a spring boot que eso representa una tabla
@Table(name = "progresoreto") //le especifica al sistema el nombre de la tabla en mysql
@Data //genera getters y setters automaticamente
@AllArgsConstructor //genera constructor con todos los atributos
@NoArgsConstructor //genera constructor sin atributos

public class ProgresoRetoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProgreso;

    // Porcentaje de avance entre 0 y 100
    private Double porcentajeAvance;

    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        no_iniciado,
        en_progreso,
        completado
    }

    // --------------------------
    // FOREIGN KEY: idInscripcion
    // --------------------------
    @ManyToOne
    @JoinColumn(name = "idInscripcion", nullable = false)
    private InscripcionModel inscripcion;

    // --------------------------
    // FOREIGN KEY: idLibro
    // --------------------------
    @ManyToOne
    @JoinColumn(name = "idLibro", nullable = false)
    private LibroModel libro;
}