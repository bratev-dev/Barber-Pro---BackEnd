package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByidCategoria(Long idCategoria);
}