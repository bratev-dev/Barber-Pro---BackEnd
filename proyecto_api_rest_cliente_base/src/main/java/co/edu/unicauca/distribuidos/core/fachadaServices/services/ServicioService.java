package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Servicio;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTORespuesta;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServicioService {

    Servicio crearServicio(ServicioDTOPeticion dto, MultipartFile imagen) throws IOException;

    List<ServicioDTORespuesta> listarServicios();

    Servicio actualizarServicio(Long idServicio, ServicioDTOPeticion dto, MultipartFile imagen);

    void eliminarServicio(Long idServicio);

    Servicio obtenerPorId(Long idServicio);

    List<ServicioDTORespuesta> listarPorCategoria(Long idCategoria);

    List<Servicio> listarActivos();
}
