package talleres.punto1.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import talleres.punto1.models.ForecastResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Autowired
    public ForecastService(WebClient webClient) {
        this.webClient = webClient;
    }

    public static class ForecastSummary {
        private double averageTemperature;
        private String generalDescription;
        private String lastUpdateTime;

        public ForecastSummary(double avgTemp, String desc, String lastUpdate) {
            this.averageTemperature = avgTemp;
            this.generalDescription = desc;
            this.lastUpdateTime = lastUpdate;
        }

        public double getAverageTemperature() { return averageTemperature; }
        public String getGeneralDescription() { return generalDescription; }
        public String getLastUpdateTime() { return lastUpdateTime; }
    }

    // Resumen próximas 24 horas
    public Mono<ForecastSummary> getForecastSummaryNext24Hours(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(ForecastResponse.class)
                .map(this::filterAndSummarizeNext24Hours);
    }

    private ForecastSummary filterAndSummarizeNext24Hours(ForecastResponse response) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime limit = now.plusHours(24);

        List<ForecastResponse.ForecastItem> filtered = response.getList().stream()
                .filter(item -> {
                    LocalDateTime dateTime = item.getLocalDateTime();
                    return !dateTime.isBefore(now) && dateTime.isBefore(limit);
                }).collect(Collectors.toList());

        return summarizeForecastItems(filtered);
    }

    // Resumen próximos 3 días
    public Mono<ForecastSummary> getForecastSummaryNext3Days(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(ForecastResponse.class)
                .map(this::filterAndSummarizeNext3Days);
    }

    private ForecastSummary filterAndSummarizeNext3Days(ForecastResponse response) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime limit = now.plusDays(3);

        List<ForecastResponse.ForecastItem> filtered = response.getList().stream()
                .filter(item -> {
                    LocalDateTime dateTime = item.getLocalDateTime();
                    return !dateTime.isBefore(now) && dateTime.isBefore(limit);
                }).collect(Collectors.toList());

        return summarizeForecastItems(filtered);
    }

    // Reutiliza para cálculos comunes
    private ForecastSummary summarizeForecastItems(List<ForecastResponse.ForecastItem> items) {
        double avgTemp = items.stream()
                .mapToDouble(item -> item.getMain().getTemp())
                .average()
                .orElse(Double.NaN);

        String description = items.isEmpty()
                ? "No data"
                : items.get(0).getWeather().get(0).getDescription();

        String lastUpdate = items.stream()
                .max(Comparator.comparing(ForecastResponse.ForecastItem::getLocalDateTime))
                .map(ForecastResponse.ForecastItem::getDtTxt)
                .orElse("No data");

        return new ForecastSummary(avgTemp, description, lastUpdate);
    }
}
