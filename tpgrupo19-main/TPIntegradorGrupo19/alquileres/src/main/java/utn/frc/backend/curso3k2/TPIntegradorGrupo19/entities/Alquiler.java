package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ALQUILERES")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long alquilerId;

    @Column(name = "ID_CLIENTE")
    private Long clienteId;

    @Column(name = "ESTADO")
    private int estado;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @OneToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;

    @Column(name = "ESTACION_RETIRO")
    private Long estacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION")
    private Long estacionDevolucion;

}
