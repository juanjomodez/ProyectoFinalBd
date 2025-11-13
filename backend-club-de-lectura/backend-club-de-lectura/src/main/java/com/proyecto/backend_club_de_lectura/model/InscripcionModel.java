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

@Entity
@Table(name = "inscripcion")
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String estadoInscripcion; // "activa", "finalizada", "cancelada"

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "idReto", nullable = false)
    private RetoLecturaModel reto;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(String estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public RetoLecturaModel getReto() {
        return reto;
    }

    public void setReto(RetoLecturaModel reto) {
        this.reto = reto;
    }
}
