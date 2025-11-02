package talleres.punto1.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class ForecastResponse {

    private List<ForecastItem> list;
    private City city;

    public List<ForecastItem> getList() { return list; }
    public void setList(List<ForecastItem> list) { this.list = list; }
    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public static class ForecastItem {
        private long dt;
        private Main main;
        private List<Weather> weather;
        @JsonProperty("dt_txt")
        private String dtTxt;

        public long getDt() { return dt; }
        public void setDt(long dt) { this.dt = dt; }
        public Main getMain() { return main; }
        public void setMain(Main main) { this.main = main; }
        public List<Weather> getWeather() { return weather; }
        public void setWeather(List<Weather> weather) { this.weather = weather; }
        public String getDtTxt() { return dtTxt; }
        public void setDtTxt(String dtTxt) { this.dtTxt = dtTxt; }

        public LocalDateTime getLocalDateTime() {
            return LocalDateTime.parse(dtTxt.replace(" ", "T"));
        }
    }

    public static class Main {
        private double temp;
        private int humidity;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }
        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    public static class Weather {
        private String main;
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class City {
        private String name;
        private String country;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
}
