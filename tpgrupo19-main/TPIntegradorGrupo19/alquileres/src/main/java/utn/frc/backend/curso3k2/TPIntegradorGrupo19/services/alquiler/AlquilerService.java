package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.alquiler;



import utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers.response.FinalizacionAlquilerResponse;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.AlquilerDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.Service;

import java.util.List;


public interface AlquilerService extends Service<AlquilerDto, Long> {

    AlquilerDto add(Long idCliente, Long estacionRetiroId);

    List<AlquilerDto> getByFilter(Long clienteId);

    FinalizacionAlquilerResponse finalizarAlquiler(Long idAlquiler, Long estacionDevolucionId, String moneda);

    double calcularCostoAlquiler(Alquiler alquiler);

}
