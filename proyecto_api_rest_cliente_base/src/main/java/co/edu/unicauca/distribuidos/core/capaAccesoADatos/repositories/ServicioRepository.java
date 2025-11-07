package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EstadoServicio;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    //Listar los servicios por categoría
    List<Servicio> findByCategoriaIdCategoria(Long idCategoria);

    //Listar los servicios Disponibles
    List<Servicio> findByEstado(EstadoServicio estado);
    //Listar los servicios Disponibles por categoria
    List<Servicio> findByCategoriaIdCategoriaAndEstado(
            Long idCategoria,
            EstadoServicio estado
    );
}
