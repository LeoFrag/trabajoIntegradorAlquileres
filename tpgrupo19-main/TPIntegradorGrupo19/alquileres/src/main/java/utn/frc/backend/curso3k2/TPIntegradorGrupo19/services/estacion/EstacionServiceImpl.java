package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.estacion;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.response.EstacionDto;

@Service
public class EstacionServiceImpl {

    public EstacionDto obtenerEstacion(Long estacionId) {
        RestTemplate restTemplate = new RestTemplate();
        if (estacionId != null) {
            ResponseEntity<EstacionDto> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8080/api/estaciones/" + estacionId,
                    EstacionDto.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Error al buscar la estacion!");
            }
        }
        return null;
    }
}
