package com.proyecto.backend_club_de_lectura.model;

import java.util.Date;

import jakarta.persistence.Entity;
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

@Entity
@Table(name = "inscripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String estadoInscripcion;

    @ManyToOne // le decimos al sistema que esta relacion es de muchos a unoo
    @JoinColumn(name = "idUsuario", nullable = false) //el join column se usa para decirle al sistema que en esta columna guarde la fk del idUsuario
    private UsuarioModel usuario; // le decimos al sistema con que tabla es la relacion

    @ManyToOne
    @JoinColumn(name = "idReto", nullable = false) //el join column se usa para decirle al sistema que en esta columna guarde la fk del idReto
    private RetoLecturaModel reto; // le decimos al sistema con que tabla es la relacion
}
