package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.EstacionTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Estacion;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;

import java.util.function.Function;

@Service
public class EstacionDtoMapper implements Function<Estacion, EstacionDto> {
    @Override
    public EstacionDto apply(Estacion estacion) {
        return new EstacionDto(
                estacion.getEstacionId(), estacion.getNombre(), estacion.getFechaHoraCreacion(),
                estacion.getLatitud(), estacion.getLongitud()
        );
    }
}
