package utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByClienteId(Long clienteId);
}
