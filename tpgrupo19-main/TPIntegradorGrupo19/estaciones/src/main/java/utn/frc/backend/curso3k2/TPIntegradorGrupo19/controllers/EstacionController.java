package utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers;


import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.EstacionesApplication;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.EstacionDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.estacion.EstacionService;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")

public class EstacionController {

    private final EstacionService svcEstacion;



    public EstacionController(EstacionService svcEstacion) {
        this.svcEstacion = svcEstacion;
    }

    @GetMapping("/{idEstacion}")
    public ResponseEntity<EstacionDto> getById(@PathVariable ("idEstacion") Long idEstacion) {

        EstacionDto estacionEncontrada = svcEstacion.getById(idEstacion);
        return ResponseEntity.ok(estacionEncontrada);

    }


    // Punto 1
    @GetMapping
    public ResponseEntity<List<EstacionDto>> getAll() {
        List<EstacionDto> values = svcEstacion.getAll();
        return ResponseEntity.ok(values);
    }

    // Punto 2
    @GetMapping("/{latitud}/{longitud}")
    public ResponseEntity<EstacionDto> getByUbicacion(@PathVariable float latitud, @PathVariable float longitud) {

        EstacionDto estacionCercana = svcEstacion.findByLatitudAndLongitud(latitud, longitud);

        if (estacionCercana != null) {
            return new ResponseEntity<>(estacionCercana, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Punto 5
    @PostMapping
    public ResponseEntity<EstacionDto> add(@RequestBody EstacionDto estacionNueva) {

        svcEstacion.add(estacionNueva);

        return new ResponseEntity<>(estacionNueva, HttpStatus.CREATED);

    }

}
