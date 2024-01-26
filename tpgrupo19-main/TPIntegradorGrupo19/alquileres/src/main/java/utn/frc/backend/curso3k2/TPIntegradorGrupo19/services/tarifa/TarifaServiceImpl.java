package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.tarifa;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.TarifaDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation.TarifaDtoMapper;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories.TarifaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements TarifaService{
    private final TarifaRepository tarifaRepository;

    private final TarifaDtoMapper tarifaDtoMapper;

    public TarifaServiceImpl(TarifaRepository tarifaRepository, TarifaDtoMapper tarifaDtoMapper) {
        this.tarifaRepository = tarifaRepository;
        this.tarifaDtoMapper = tarifaDtoMapper;
    }

    @Override
    public List<TarifaDto> getAll() {
        List<Tarifa> values = tarifaRepository.findAll();
        return values.stream().map(tarifaDtoMapper).toList();
    }

    @Override
    public TarifaDto getById(Long tarifaId){
        Optional<Tarifa> tarifaBuscada = tarifaRepository.findById(tarifaId);
        return tarifaBuscada.map(tarifaDtoMapper).orElseThrow();

    }
}