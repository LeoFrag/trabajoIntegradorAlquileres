package utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers.response.FinalizacionAlquilerResponse;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.AlquilerDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.alquiler.AlquilerService;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")

public class AlquilerController {
    private final AlquilerService svcAlquiler;

    public AlquilerController(AlquilerService svcAlquiler) {
        this.svcAlquiler = svcAlquiler;
    }
    @GetMapping
    public ResponseEntity<List<AlquilerDto>> getAll() {
        List<AlquilerDto> values = svcAlquiler.getAll();
        return ResponseEntity.ok(values);
    }


    // Punto 3
    @PostMapping
    public ResponseEntity<AlquilerDto> add(@RequestParam Long idCliente,
                                           @RequestParam Long estacionRetiroId) {

       AlquilerDto alquilerNuevo = svcAlquiler.add(idCliente, estacionRetiroId);

       return new ResponseEntity<>(alquilerNuevo, HttpStatus.CREATED);

    }

    @PostMapping("/{idAlquiler}")
    public ResponseEntity<FinalizacionAlquilerResponse> finalizarAlquiler(

            @PathVariable ("idAlquiler") Long idAlquiler,
            @RequestParam ("estacionDevolucionId") Long estacionDevolucionId,
            @RequestParam(defaultValue = "ARS") String moneda
    ) {
        FinalizacionAlquilerResponse response = svcAlquiler.finalizarAlquiler(idAlquiler, estacionDevolucionId, moneda);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Punto 6
    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<AlquilerDto>> getByFilter(@PathVariable Long clientId) {
        List<AlquilerDto> alquileresEncontrados =  svcAlquiler.getByFilter(clientId);
        return ResponseEntity.ok(alquileresEncontrados);
    }
}
