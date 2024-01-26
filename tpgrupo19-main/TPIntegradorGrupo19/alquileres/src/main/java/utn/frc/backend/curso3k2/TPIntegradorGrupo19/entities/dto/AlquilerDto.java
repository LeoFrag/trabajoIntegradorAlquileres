package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.response.EstacionDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDto {
    private Long alquilerId;
    private Long clienteId;
    private int estado;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private double monto;
    private Tarifa tarifa;
    private EstacionDto estacionRetiro;
    private EstacionDto estacionDevolucion;


    public AlquilerDto(Long alquilerId, Long clienteId, int estado, LocalDateTime fechaHoraRetiro, LocalDateTime fechaHoraDevolucion, double monto, Tarifa tarifa) {
        this.alquilerId = alquilerId;
        this.clienteId = clienteId;
        this.estado = estado;
        this.fechaHoraRetiro = fechaHoraRetiro;
        this.fechaHoraDevolucion = fechaHoraDevolucion;
        this.monto = monto;
        this.tarifa = tarifa;
    }


}
