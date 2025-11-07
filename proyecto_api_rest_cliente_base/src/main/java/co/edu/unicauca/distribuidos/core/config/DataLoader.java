package co.edu.unicauca.distribuidos.core.config;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Categoria;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.Servicio;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EstadoServicio;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.CategoriaRepository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(CategoriaRepository categoriaRepo, ServicioRepository servicioRepo) {
        return args -> {

            // Crear categorías iniciales
            Categoria corte = new Categoria("Corte");
            Categoria barba = new Categoria("Barba");
            Categoria tinte = new Categoria("Tinte");

            categoriaRepo.save(corte);
            categoriaRepo.save(barba);
            categoriaRepo.save(tinte);

            // Crear servicios iniciales (sin imagen por ahora)
            servicioRepo.save(new Servicio(
                    null,
                    "Corte Fade",
                    "Incluye espuma y lavado",
                    25000.0,
                    "Disponible",
                    20,
                    "1761801773777_barbillita.jpg",
                    corte
            ));

            servicioRepo.save(new Servicio(
                    null,
                    "Arreglo de Barba",
                    "Perfilado y cuidados",
                    18000.0,
                    "Disponible",
                    20,
                    null,
                    barba
            ));

            System.out.println("✅ Datos iniciales cargados en H2 correctamente.");
        };
    }
}