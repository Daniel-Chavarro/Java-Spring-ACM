package talleres.punto1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import talleres.punto1.service.ForecastService;
import talleres.punto1.service.ForecastService.ForecastSummary;

@RestController
@RequestMapping("/api/forecast")
public class ForecastControl {

    private final ForecastService forecastService;

    @Autowired
    public ForecastControl(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    // Endpoint para el resumen de las próximas 24 horas
    @GetMapping("/summary/24h")
    public Mono<ForecastSummary> getSummaryNext24Hours(@RequestParam String city) {
        return forecastService.getForecastSummaryNext24Hours(city);
    }

    // Endpoint para el resumen de los próximos 3 días
    @GetMapping("/summary/3days")
    public Mono<ForecastSummary> getSummaryNext3Days(@RequestParam String city) {
        return forecastService.getForecastSummaryNext3Days(city);
    }
}
