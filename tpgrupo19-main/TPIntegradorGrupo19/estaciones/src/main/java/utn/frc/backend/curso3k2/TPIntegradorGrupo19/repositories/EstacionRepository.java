package utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Estacion;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;

import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion,Long> {

    EstacionDto findByLatitudAndLongitud(float latitud, float longitud);

}
