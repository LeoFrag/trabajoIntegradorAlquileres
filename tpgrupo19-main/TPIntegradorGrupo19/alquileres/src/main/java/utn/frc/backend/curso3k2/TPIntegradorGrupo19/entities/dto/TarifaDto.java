package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDto {
    private Long tarifaId;
    private int tipoTarifa;
    private int definicion;
    private int diaSemana;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private float montoFijoAlquiler;
    private float montoMinutoFraccion;
    private float montoKm;
    private float montoHora;
}