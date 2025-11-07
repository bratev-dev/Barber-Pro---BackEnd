package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.CategoriaDTORespuesta;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTORespuesta;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Categoria;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Servicio;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EstadoServicio;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ServicioRepository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService{

    @Autowired
    private ServicioRepository servicioRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private ModelMapper mapper;

    private final String UPLOAD_DIR = "uploads/";


    //Metodo para Agregar El Servicio
    @Override
    public Servicio crearServicio(ServicioDTOPeticion dto, MultipartFile imagen) throws IOException{

        Servicio servicio = new Servicio();
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(dto.getPrecio());
        servicio.setEstado(dto.getEstado());
        servicio.setDuracion(dto.getDuracion());

        //mapper.map(dto, servicio);
        Categoria categoria = categoriaRepo.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        servicio.setCategoria(categoria);

        if (imagen != null && !imagen.isEmpty()) {
            String ruta = guardarImagen(imagen);
            servicio.setImagenUrl(ruta);
        }

        return servicioRepo.save(servicio);
    }
    // Listar todos los Servicios
    @Override
    public List<ServicioDTORespuesta> listarServicios() {
        List<Servicio> servicios = servicioRepo.findAll();

        List<ServicioDTORespuesta> servicioDTORespuestas = new ArrayList<>();

        for (Servicio servicio : servicios) {

            // Crear el DTO de categoría (porque tu ServicioDTORespuesta la incluye)
            CategoriaDTORespuesta categoriaDTO = new CategoriaDTORespuesta();
            categoriaDTO.setIdCategoria(servicio.getCategoria().getIdCategoria());
            categoriaDTO.setNombreCategoria(servicio.getCategoria().getNombreCategoria());

            // Crear el DTO de servicio
            ServicioDTORespuesta dto = new ServicioDTORespuesta();
            dto.setIdServicio(servicio.getIdServicio());
            dto.setNombre(servicio.getNombre());
            dto.setDescripcion(servicio.getDescripcion());
            dto.setPrecio(servicio.getPrecio());
            dto.setEstado(servicio.getEstado());
            dto.setDuracion(servicio.getDuracion());
            dto.setImagenUrl(servicio.getImagenUrl());
            dto.setCategoria(categoriaDTO);

            // Agregarlo a la lista final
            servicioDTORespuestas.add(dto);
        }

        return servicioDTORespuestas;

    }

    // Actualizar Servicio en particular
    @Override
    public Servicio actualizarServicio(Long idServicio, ServicioDTOPeticion dto, MultipartFile imagen) {
        Servicio servicio = servicioRepo.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(dto.getPrecio());
        servicio.setEstado(dto.getEstado());
        servicio.setDuracion(dto.getDuracion());

        Categoria categoria = categoriaRepo.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        servicio.setCategoria(categoria);
        System.out.println(dto.getCategoria().getIdCategoria());
        servicio.setEstado(dto.getEstado());

        if (imagen != null && !imagen.isEmpty()) {
            String ruta = guardarImagen(imagen);
            servicio.setImagenUrl(ruta);
        }

        return servicioRepo.save(servicio);
    }

    //Eliminación del servicio
    @Override
    public void eliminarServicio(Long idServicio) {
        servicioRepo.deleteById(idServicio);
    }

    // Buscar Servicio por ID
    @Override
    public Servicio obtenerPorId(Long idServicio) {
        return servicioRepo.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }

    @Override
    public List<ServicioDTORespuesta> listarPorCategoria(Long idCategoria) {
        List<Servicio> servicios = servicioRepo.findByCategoriaIdCategoria(idCategoria);

        List<ServicioDTORespuesta> servicioDTORespuestas = new ArrayList<>();

        for (Servicio servicio : servicios) {

            // Crear el DTO de categoría (porque tu ServicioDTORespuesta la incluye)
            CategoriaDTORespuesta categoriaDTO = new CategoriaDTORespuesta();
            categoriaDTO.setIdCategoria(servicio.getCategoria().getIdCategoria());
            categoriaDTO.setNombreCategoria(servicio.getCategoria().getNombreCategoria());

            // Crear el DTO de servicio
            ServicioDTORespuesta dto = new ServicioDTORespuesta();
            dto.setNombre(servicio.getNombre());
            dto.setDescripcion(servicio.getDescripcion());
            dto.setPrecio(servicio.getPrecio());
            dto.setEstado(servicio.getEstado());
            dto.setDuracion(servicio.getDuracion());
            dto.setImagenUrl(servicio.getImagenUrl());
            dto.setCategoria(categoriaDTO);

            // Agregarlo a la lista final
            servicioDTORespuestas.add(dto);
        }

        return servicioDTORespuestas;
    }

    //Listar Servicios Activos
    @Override
    public List<Servicio> listarActivos() {
        return servicioRepo.findByEstado(EstadoServicio.ACTIVO);
    }

    // Metodo para guardar la ruta de la imagen
    private String guardarImagen(MultipartFile imagen) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            Path rutaArchivo = uploadPath.resolve(nombreArchivo);
            Files.copy(imagen.getInputStream(), rutaArchivo);

            return nombreArchivo; // Guardamos solo el nombre
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }
}

