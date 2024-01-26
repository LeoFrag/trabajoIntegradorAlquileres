package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.estacion;

import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.Service;

public interface EstacionService extends Service<EstacionDto, Long> {

    EstacionDto findByLatitudAndLongitud(float latitud, float longitud);
}
