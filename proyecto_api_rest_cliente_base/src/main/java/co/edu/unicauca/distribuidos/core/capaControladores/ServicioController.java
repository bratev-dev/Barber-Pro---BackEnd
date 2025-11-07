package co.edu.unicauca.distribuidos.core.capaControladores;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Servicio;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTORespuesta;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;


    //Creación del servicio
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Servicio> crearServicio(
            @RequestPart("datos") ServicioDTOPeticion dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {
        return ResponseEntity.ok(servicioService.crearServicio(dto,imagen));
    }

    //Listar Servicios
    @GetMapping
    public List<ServicioDTORespuesta> listarServicios() {
        return servicioService.listarServicios();
    }

    //Buscar servicio por ID
    @GetMapping("/{id}")
    public Servicio obtenerPorId(@PathVariable Long id) {
        return servicioService.obtenerPorId(id);
    }

    //Actualizar Servicio
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Servicio> actualizarServicio(
            @PathVariable Long id,
            @RequestPart("datos") ServicioDTOPeticion dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        return ResponseEntity.ok(servicioService.actualizarServicio(id, dto, imagen));
    }

    //Eliminar Servicio
    @DeleteMapping("/{id}")
    public void eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
    }

    //
    @GetMapping("/categoria/{idCategoria}")
    public List<ServicioDTORespuesta> listarPorCategoria(@PathVariable Long idCategoria) {
        return servicioService.listarPorCategoria(idCategoria);
    }
}