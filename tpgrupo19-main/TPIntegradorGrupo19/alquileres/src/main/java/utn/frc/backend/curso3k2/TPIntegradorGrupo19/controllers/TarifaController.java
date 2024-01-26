package utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.TarifaDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.tarifa.TarifaService;

import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {

    private final TarifaService tarifaService;


    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @GetMapping
    public ResponseEntity<List<TarifaDto>> getAll() {
        List<TarifaDto> values = tarifaService.getAll();
        return ResponseEntity.ok(values);
    }
}

