package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Categoria;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepo;

    //Creación para Categoria
    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    //Listar Categorias
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    // Buscar Categoría por id
    @Override
    public Categoria obtenerPorId(Long idCategoria) {
        return categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }
}
