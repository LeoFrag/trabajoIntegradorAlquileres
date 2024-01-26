package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.estacion;

import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Estacion;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.EstacionTransformation.EstacionDtoMapper;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.EstacionTransformation.EstacionMapper;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories.EstacionRepository;

import java.io.Serial;
import java.util.List;
import java.util.Optional;


@Service
public class EstacionServiceImpl implements EstacionService {

    private final EstacionRepository estacionRepository;

    private final EstacionDtoMapper estacionDtoMapper;

    private final EstacionMapper estacionMapper;


    public EstacionServiceImpl(EstacionRepository estacionRepository, EstacionDtoMapper estacionDtoMapper, EstacionMapper estacionMapper) {
        this.estacionRepository = estacionRepository;
        this.estacionDtoMapper = estacionDtoMapper;
        this.estacionMapper = estacionMapper;
    }


    @Override
    public void add(EstacionDto entity) {
        Estacion estacionNueva = estacionMapper.apply(entity);
        estacionRepository.save(estacionNueva);

    }
    @Override
    public EstacionDto getById(Long id) {
        Optional<Estacion> estacionEncontrada = estacionRepository.findById(id);
        return estacionEncontrada.map(estacionDtoMapper).orElseThrow();
    }

    @Override
    public List<EstacionDto> getAll() {
        List<Estacion> values = estacionRepository.findAll();
        return values.stream().map(estacionDtoMapper).toList();
    }

    @Override
    public EstacionDto findByLatitudAndLongitud(float latitud, float longitud) {
        List<Estacion> estaciones = estacionRepository.findAll();
        Estacion estacionCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = distanciaEuclidiana(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionCercana = estacion;
            }
        }

        return estacionCercana != null ? estacionDtoMapper.apply(estacionCercana) : null;
    }

    private double distanciaEuclidiana(float lat1, float lon1, float lat2, float lon2) {
        final double GRADO_A_METROS = 110000.0; // Cada grado corresponde a 110,000 metros aproximadamente

        // Diferencias en grados
        double diferenciaLatitud = lat2 - lat1;
        double diferenciaLongitud = lon2 - lon1;

        // Distancia euclidiana utilizando la equivalencia dada
        double distancia = Math.sqrt(Math.pow(diferenciaLatitud * GRADO_A_METROS, 2) + Math.pow(diferenciaLongitud * GRADO_A_METROS, 2));

        return distancia;
    }


}
