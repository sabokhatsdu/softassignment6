import java.util.ArrayList;
import java.util.List;

class WeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;

    public WeatherData(double temperature, double humidity, double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}

interface WeatherProvider {
    WeatherData getCurrentWeather(String location);
    List<WeatherData> getForecast(String location);
}

class WeatherAdapter implements WeatherProvider {
    private OpenWeatherMapClient openWeatherMapClient;
    private AccuWeatherClient accuWeatherClient;

    public WeatherAdapter(OpenWeatherMapClient openWeatherMapClient, AccuWeatherClient accuWeatherClient) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.accuWeatherClient = accuWeatherClient;
    }

    @Override
    public WeatherData getCurrentWeather(String location) {
        try {
            OpenWeatherMapData openWeatherMapData = openWeatherMapClient.getCurrentWeather(location);
            return new WeatherData(openWeatherMapData.getTemperature(), openWeatherMapData.getHumidity(), openWeatherMapData.getWindSpeed());
        } catch (WeatherServiceException e) {
            System.err.println("Error fetching current weather data from OpenWeatherMap: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<WeatherData> getForecast(String location) {
        List<WeatherData> forecast = new ArrayList<>();
        try {
            List<AccuWeatherData> accuWeatherDataList = accuWeatherClient.getForecast(location);
            for (AccuWeatherData accuWeatherData : accuWeatherDataList) {
                forecast.add(new WeatherData(accuWeatherData.getTemperature(), accuWeatherData.getHumidity(), accuWeatherData.getWindSpeed()));
            }
        } catch (WeatherServiceException e) {
            System.err.println("Error fetching forecast data from AccuWeather: " + e.getMessage());
        }
        return forecast;
    }
}

class OpenWeatherMapClient {
    public OpenWeatherMapData getCurrentWeather(String location) throws WeatherServiceException {
        return new OpenWeatherMapData(25.0, 60.0, 10.0);
    }
}

class AccuWeatherClient {
    public List<AccuWeatherData> getForecast(String location) throws WeatherServiceException {
        List<AccuWeatherData> forecast = new ArrayList<>();
        forecast.add(new AccuWeatherData(20.0, 50.0, 15.0));
        forecast.add(new AccuWeatherData(22.0, 55.0, 12.0));
        forecast.add(new AccuWeatherData(18.0, 45.0, 17.0));
        return forecast;
    }
}

class OpenWeatherMapData {
    private double temperature;
    private double humidity;
    private double windSpeed;

    public OpenWeatherMapData(double temperature, double humidity, double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}

class AccuWeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;

    public AccuWeatherData(double temperature, double humidity, double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}

class WeatherServiceException extends Exception {
    public WeatherServiceException(String message) {
        super(message);
    }
}

public class assignment6pt3 {

    public static void main(String[] args) {
        WeatherAdapter adapter = new WeatherAdapter(new OpenWeatherMapClient(), new AccuWeatherClient());

        WeatherData currentWeather = adapter.getCurrentWeather("New York");
        if (currentWeather != null) {
            System.out.println("Current Weather:");
            System.out.println("Temperature: " + currentWeather.getTemperature() + "°C");
            System.out.println("Humidity: " + currentWeather.getHumidity() + "%");
            System.out.println("Wind Speed: " + currentWeather.getWindSpeed() + " km/h");
        } else {
            System.out.println("Failed to fetch current weather data.");
        }

        List<WeatherData> forecast = adapter.getForecast("New York");
        if (!forecast.isEmpty()) {
            System.out.println("\nForecast:");
            for (int i = 0; i < forecast.size(); i++) {
                WeatherData data = forecast.get(i);
                System.out.println("Day " + (i + 1) + ":");
                System.out.println("Temperature: " + data.getTemperature() + "°C");
                System.out.println("Humidity: " + data.getHumidity() + "%");
                System.out.println("Wind Speed: " + data.getWindSpeed() + " km/h");
            }
        } else {
            System.out.println("Failed to fetch forecast data.");
        }
    }
}
