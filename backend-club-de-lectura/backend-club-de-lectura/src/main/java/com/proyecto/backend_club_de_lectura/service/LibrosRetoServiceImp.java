import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.model.LibrosRetoModel;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.repository.ILibroRepository;
import com.proyecto.backend_club_de_lectura.repository.ILibrosRetoRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;
import com.proyecto.backend_club_de_lectura.service.ILibrosRetoService;

@Service
public class LibrosRetoServiceImp implements ILibrosRetoService {

    @Autowired
    private ILibrosRetoRepository librosRetoRepository;

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private IRetoLecturaRepository retoRepository;

    @Override
    public LibrosRetoModel agregarLibroAReto(Integer idLibro, Integer idReto) {

        // Validar duplicado
        if (librosRetoRepository.existsByLibro_IdLibroAndReto_IdReto(idLibro, idReto)) {
            throw new RuntimeException("El libro ya está asociado a este reto.");
        }

        // Buscar libro
        LibroModel libro = libroRepository.findById(idLibro).orElse(null);
        if (libro == null) {
            throw new RuntimeException("Libro no encontrado.");
        }

        // Buscar reto
        RetoLecturaModel reto = retoRepository.findById(idReto).orElse(null);
        if (reto == null) {
            throw new RuntimeException("Reto no encontrado.");
        }

        // Crear relación (forma básica)
        LibrosRetoModel relacion = new LibrosRetoModel();
        relacion.setLibro(libro);
        relacion.setReto(reto);

        return librosRetoRepository.save(relacion);
    }

    @Override
    public List<LibrosRetoModel> obtenerLibrosPorReto(Integer idReto) {
        List<LibrosRetoModel> lista = librosRetoRepository.findAll();

        // FILTRADO BÁSICO, como lo vieron en clase
        List<LibrosRetoModel> resultado = new java.util.ArrayList<>();

        for (LibrosRetoModel lr : lista) {
            if (lr.getReto().getIdReto() == idReto) {
                resultado.add(lr);
            }
        }

        return resultado;
    }

    @Override
    public void eliminarLibroDeReto(Integer idLibrosReto) {
        librosRetoRepository.deleteById(idLibrosReto);
    }
}
