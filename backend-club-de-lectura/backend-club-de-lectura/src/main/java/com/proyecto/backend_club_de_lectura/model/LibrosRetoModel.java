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

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "libros_reto") // Mapea la entidad a la tabla "libros_reto" en la base de datos
@Data // Genera getters, setters y otros métodos útiles automáticamente
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor // Genera un constructor sin atributos

public class LibrosRetoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idLibrosReto;

    // ---------------------------
    // FK hacia Libro
    // ---------------------------
    @ManyToOne
    @JoinColumn(name = "idLibro", nullable = false)
    private LibroModel libro;

    // ---------------------------
    // FK hacia RetoLectura
    // ---------------------------
    @ManyToOne
    @JoinColumn(name = "idReto", nullable = false)
    private RetoLecturaModel reto;

}