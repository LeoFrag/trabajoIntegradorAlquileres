package utn.frc.backend.curso3k2.TPIntegradorGrupo19.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDto {

    private Long estacionId;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private float latitud;
    private float longitud;
}
