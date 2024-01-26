package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.AlquilerDto;

import java.util.function.Function;

@Service
public class AlquilerMapper implements Function<AlquilerDto, Alquiler> {
    @Override
    public Alquiler apply(AlquilerDto alquilerDto) {
        return new Alquiler(
                alquilerDto.getAlquilerId(),alquilerDto.getClienteId(), alquilerDto.getEstado(),
                 alquilerDto.getFechaHoraRetiro(),
                alquilerDto.getFechaHoraDevolucion(), alquilerDto.getMonto(), alquilerDto.getTarifa(),
                alquilerDto.getEstacionRetiro().getEstacionId(), alquilerDto.getEstacionDevolucion().getEstacionId());
    }
}
