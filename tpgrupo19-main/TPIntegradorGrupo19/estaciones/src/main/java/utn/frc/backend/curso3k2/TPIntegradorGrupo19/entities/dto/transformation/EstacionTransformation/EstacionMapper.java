package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.EstacionTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Estacion;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;

import java.util.function.Function;

@Service
public class EstacionMapper implements Function<EstacionDto, Estacion> {
    @Override
    public Estacion apply(EstacionDto estacionDto) {
        return new Estacion(
                estacionDto.getEstacionId(), estacionDto.getNombre(), estacionDto.getFechaHoraCreacion(),
                estacionDto.getLatitud(), estacionDto.getLongitud());
    }
}
