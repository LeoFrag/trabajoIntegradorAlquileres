package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.currency;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.response.ClassResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionImpl {


    public double convertCurrency(String targetCurrency, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("moneda_destino", targetCurrency);
        requestBody.put("importe", amount);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<ClassResponse> response = restTemplate.postForEntity(
                "http://34.82.105.125:8080/convertir",
                requestEntity,
                ClassResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getImporte();
        } else {
            throw new RuntimeException("Error al realizar la conversión de moneda");
        }


    }
}
