package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.TarifaDto;

import java.util.function.Function;

@Service
public class TarifaDtoMapper implements Function<Tarifa, TarifaDto> {
    @Override
    public TarifaDto apply(Tarifa tarifa) {
        return new TarifaDto(
                tarifa.getTarifaId(), tarifa.getTipoTarifa(), tarifa.getDefinicion(),
                tarifa.getDiaSemana(), tarifa.getDiaMes(), tarifa.getMes(),
                tarifa.getAnio(), tarifa.getMontoFijoAlquiler(), tarifa.getMontoMinutoFraccion(),
                tarifa.getMontoKm(), tarifa.getMontoHora()
        );
    }
}
