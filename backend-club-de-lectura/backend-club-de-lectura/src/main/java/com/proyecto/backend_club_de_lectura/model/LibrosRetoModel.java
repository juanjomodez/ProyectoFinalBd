package com.proyecto.backend_club_de_lectura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // indica que esta clase es una entidad jpa
@Table(name = "librosreto") // 
@Data // genera getters y setters
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor // Genera un constructor sin atributos

public class LibrosRetoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idLibrosReto;

    
    @ManyToOne
    @JoinColumn(name = "idLibro", nullable = false)
    private LibroModel libro;

   
    @ManyToOne
    @JoinColumn(name = "idReto", nullable = false)
    private RetoLecturaModel reto;

}