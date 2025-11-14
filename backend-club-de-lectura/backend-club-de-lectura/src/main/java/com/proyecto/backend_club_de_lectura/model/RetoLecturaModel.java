package com.proyecto.backend_club_de_lectura.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "retolectura") 
@Data               // el data nos genera getters y setters automaticamente
@AllArgsConstructor // es un constructor con todos los campos
@NoArgsConstructor  // es un constructor vacío
public class RetoLecturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//es para que el valor de id se 
    // coloque automaticamente y no toque hacer id manualmente
    private int idReto;

    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
}
