package utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    Tarifa findTarifaByDiaSemana(int diaSemana);

    Tarifa findTarifaByDiaMesAndMesAndAnio(int diaMes, int mes, int anio);
}
