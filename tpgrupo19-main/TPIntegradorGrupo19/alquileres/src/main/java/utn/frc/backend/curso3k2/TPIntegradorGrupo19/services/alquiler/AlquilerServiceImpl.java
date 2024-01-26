package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.alquiler;


import org.springframework.stereotype.Service;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.controllers.response.FinalizacionAlquilerResponse;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Alquiler;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.Tarifa;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.AlquilerDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.response.EstacionDto;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation.AlquilerDtoMapper;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.entities.dto.transformation.AlquilerTransformation.AlquilerMapper;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories.AlquilerRepository;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.repositories.TarifaRepository;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.currency.CurrencyConversionImpl;
import utn.frc.backend.curso3k2.TPIntegradorGrupo19.services.estacion.EstacionServiceImpl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;

    private final AlquilerDtoMapper alquilerDtoMapper;

    private final AlquilerMapper alquilerMapper;

    private final TarifaRepository tarifaRepository;

    private final CurrencyConversionImpl currencyConversion;

    private final EstacionServiceImpl estacionService;


    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, AlquilerDtoMapper alquilerDtoMapper, AlquilerMapper alquilerMapper, TarifaRepository tarifaRepository, CurrencyConversionImpl currencyConversion, EstacionServiceImpl estacionService) {
        this.alquilerRepository = alquilerRepository;
        this.alquilerDtoMapper = alquilerDtoMapper;
        this.alquilerMapper = alquilerMapper;
        this.tarifaRepository = tarifaRepository;
        this.currencyConversion = currencyConversion;

        this.estacionService = estacionService;
    }


    @Override
    public AlquilerDto add(Long idCliente, Long estacionRetiroId){

        EstacionDto estacionRetiro = estacionService.obtenerEstacion(estacionRetiroId);
        Alquiler alquiler = new Alquiler();
        alquiler.setClienteId(idCliente);
        alquiler.setEstado(1); // Estado 1 - iniciado
        alquiler.setEstacionRetiro(estacionRetiroId);
        alquiler.setFechaHoraRetiro(LocalDateTime.now()); // Fecha y hora actual

        alquilerRepository.save(alquiler);
        AlquilerDto alquilerDto = alquilerDtoMapper.apply(alquiler);
        alquilerDto.setEstacionRetiro(estacionRetiro);

        return alquilerDto;

    }

    @Override
    public List<AlquilerDto> getAll() {
        List<Alquiler> values = alquilerRepository.findAll();
        List<AlquilerDto> alquileresDto = new ArrayList<>();

        for (Alquiler alquiler : values) {
            Long estacionRetiroId = alquiler.getEstacionRetiro();
            Long estacionDevolucionId = alquiler.getEstacionDevolucion();

            EstacionDto estacionRetiroDto = estacionService.obtenerEstacion(estacionRetiroId);
            EstacionDto estacionDevolucionDto = estacionService.obtenerEstacion(estacionDevolucionId);

            AlquilerDto alquilerDto = alquilerDtoMapper.apply(alquiler);
            alquilerDto.setEstacionRetiro(estacionRetiroDto);
            alquilerDto.setEstacionDevolucion(estacionDevolucionDto);

            alquileresDto.add(alquilerDto);
        }

        return alquileresDto;
    }

    @Override
    public AlquilerDto getById(Long alquilerId){
        Optional<Alquiler> alquilerBuscado = alquilerRepository.findById(alquilerId);
        return alquilerBuscado.map(alquilerDtoMapper).orElseThrow();
    }


    @Override
    public List<AlquilerDto> getByFilter(Long clientId) {
        List<Alquiler> listaAlquileres = alquilerRepository.findByClienteId(clientId);
        List<AlquilerDto> alquileresDto = new ArrayList<>();

        for (Alquiler alquiler : listaAlquileres) {
            Long estacionRetiroId = alquiler.getEstacionRetiro();
            Long estacionDevolucionId = alquiler.getEstacionDevolucion();

            EstacionDto estacionRetiroDto = estacionService.obtenerEstacion(estacionRetiroId);
            EstacionDto estacionDevolucionDto = estacionService.obtenerEstacion(estacionDevolucionId);

            AlquilerDto alquilerDto = alquilerDtoMapper.apply(alquiler);
            alquilerDto.setEstacionRetiro(estacionRetiroDto);
            alquilerDto.setEstacionDevolucion(estacionDevolucionDto);

            alquileresDto.add(alquilerDto);
        }

        return alquileresDto;


    }

    @Override
    public FinalizacionAlquilerResponse finalizarAlquiler(Long idAlquiler, Long estacionDevolucionId, String moneda) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).orElseThrow();

        alquiler.setEstado(2);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        EstacionDto estacionDevolucion = estacionService.obtenerEstacion(estacionDevolucionId);
        alquiler.setEstacionDevolucion(estacionDevolucionId);


        LocalDateTime fechaActual = LocalDateTime.now();
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();
        int diaMes = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int anio = fechaActual.getYear();

        Tarifa tarifaDiaSemana = tarifaRepository.findTarifaByDiaSemana(diaSemana.getValue());
        Tarifa tarifaDiaMesAnio = tarifaRepository.findTarifaByDiaMesAndMesAndAnio(diaMes, mes, anio);

        Tarifa tarifa = (tarifaDiaMesAnio != null) ? tarifaDiaMesAnio : tarifaDiaSemana;

        alquiler.setTarifa(tarifa);

        double monto = calcularCostoAlquiler(alquiler);

        // Realiza la conversión de moneda si es necesario
        if (!moneda.equalsIgnoreCase("ARS")) {
            try {
                monto = currencyConversion.convertCurrency(moneda, monto);
            } catch (Exception e) {
                throw new RuntimeException("Error al convertir a la moneda: " + moneda);
            }
        }

        alquiler.setMonto(monto);
        alquilerRepository.save(alquiler);

        return new FinalizacionAlquilerResponse(alquiler, moneda);
    }


    @Override
    public double calcularCostoAlquiler(Alquiler alquiler) {
        Tarifa tarifa = alquiler.getTarifa();

        double costo = tarifa.getMontoFijoAlquiler();

        int minutosFraccion = (int) Duration.between(alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion()).toMinutes();

        int horasCompletas = minutosFraccion / 60;
        costo += tarifa.getMontoHora() * horasCompletas;

        int minutosResto = minutosFraccion % 60;

        if (minutosResto > 0 && minutosResto < 31) {
            costo += tarifa.getMontoMinutoFraccion() * minutosResto;
        }else {
            costo += tarifa.getMontoHora();
        }

        EstacionDto estacionRetiro = estacionService.obtenerEstacion(alquiler.getEstacionRetiro());
        EstacionDto estacionDevolucion = estacionService.obtenerEstacion(alquiler.getEstacionDevolucion());
        double distanciaLat = (estacionRetiro.getLatitud() - estacionDevolucion.getLatitud()) * 110;
        double distanciaLon = (estacionRetiro.getLongitud() - estacionDevolucion.getLongitud()) * 110;

        // Calcula la distancia euclidiana
        double distancia = Math.sqrt(Math.pow(distanciaLat, 2) + Math.pow(distanciaLon, 2));

        costo += tarifa.getMontoKm() * distancia;

        return costo;
    }


}