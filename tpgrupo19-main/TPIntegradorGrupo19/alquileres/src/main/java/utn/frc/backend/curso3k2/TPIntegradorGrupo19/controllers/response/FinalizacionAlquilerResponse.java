package utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalizacionAlquilerResponse {

    Alquiler alquiler;
    String monedaElegida;

}