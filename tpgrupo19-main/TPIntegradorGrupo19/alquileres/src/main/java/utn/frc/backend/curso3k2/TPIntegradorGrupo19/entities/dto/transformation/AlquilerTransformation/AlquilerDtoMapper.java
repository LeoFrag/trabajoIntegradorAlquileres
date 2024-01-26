package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.AlquilerDto;

import java.util.function.Function;

@Service
public class AlquilerDtoMapper implements Function<Alquiler, AlquilerDto> {
    @Override
    public AlquilerDto apply(Alquiler alquiler){
        return new AlquilerDto(
            alquiler.getAlquilerId(),alquiler.getClienteId(), alquiler.getEstado(), alquiler.getFechaHoraRetiro(),
            alquiler.getFechaHoraDevolucion(), alquiler.getMonto(), alquiler.getTarifa());
}

}
