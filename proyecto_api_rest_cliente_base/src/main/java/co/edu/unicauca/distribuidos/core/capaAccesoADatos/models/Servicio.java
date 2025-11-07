package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Categoria;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    private String nombre;

    private String descripcion;

    private Double precio;

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoServicio estado; // "ACTIVO" o "INACTIVO"
    */

    private String estado;

    private Integer duracion;

    private String imagenUrl; // Nombre del archivo o URL pública

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    // Getters y setters
}
