package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.TarifaDto;

import java.util.function.Function;

@Service
public class TarifaMapper implements Function<TarifaDto, Tarifa> {
    @Override
    public Tarifa apply(TarifaDto tarifaDto) {
        return new Tarifa(
                tarifaDto.getTarifaId(), tarifaDto.getTipoTarifa(), tarifaDto.getDefinicion(),
                tarifaDto.getDiaSemana(), tarifaDto.getDiaMes(), tarifaDto.getMes(),
                tarifaDto.getAnio(), tarifaDto.getMontoFijoAlquiler(), tarifaDto.getMontoMinutoFraccion(),
                tarifaDto.getMontoKm(), tarifaDto.getMontoHora());
    }
}