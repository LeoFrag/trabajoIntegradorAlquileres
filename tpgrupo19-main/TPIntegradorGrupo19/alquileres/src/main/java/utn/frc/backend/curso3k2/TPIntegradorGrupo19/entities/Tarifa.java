package utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TARIFAS")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long tarifaId;

    @Column(name = "TIPO_TARIFA")
    private int tipoTarifa;

    @Column(name = "DEFINICION")
    private int definicion;

    @Column(name = "DIA_SEMANA")
    private int diaSemana;

    @Column(name = "DIA_MES")
    private Integer diaMes;

    @Column(name = "MES")
    private Integer mes;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private float montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private float montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private float montoKm;

    @Column(name = "MONTO_HORA")
    private float montoHora;
}
