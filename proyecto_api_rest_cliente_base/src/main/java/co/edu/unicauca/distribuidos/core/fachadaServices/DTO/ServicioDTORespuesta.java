package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicioDTORespuesta {
    private Long idServicio;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private String imagenUrl;
    private Integer duracion;
    private CategoriaDTORespuesta categoria;
}

