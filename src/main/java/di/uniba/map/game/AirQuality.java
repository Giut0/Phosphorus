package di.uniba.map.game;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
/**
 * The AirQuality class provides methods to fetch and analyze air quality data.
 */
public class AirQuality {

    // Endpoint
    private final String AIR_QUALITY_URL = "https://air-quality-api.open-meteo.com/v1/air-quality?latitude=41.1207&longitude=16.8698&current=european_aqi,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone,uv_index";

    private final String GOOD = "GOOD";
    private final String FAIR = "FAIR";
    private final String POOR = "POOR";

    private int europeanAqi;
    private double ozone;
    private double sulphurDioxide;
    private double nitrogenDioxide;
    private double uvIndex;
    private boolean requestStatus;

    /**
     * Constructor for the AirQuality class.
     * Fetches air quality data and sets the class variables.
     */
    public AirQuality() {
        try {
            Map<String, Object> response = this.airQualityApiCall();

            this.setEuropeanAqi((int) response.get("european_aqi"));
            this.setOzone((double) response.get("ozone"));
            this.setSulphurDioxide((double) response.get("sulphur_dioxide"));
            this.setNitrogenDioxide((double) response.get("nitrogen_dioxide"));
            this.setUvIndex((double) response.get("uv_index"));

            this.setRequestStatus(true);

        } catch (Exception e) {
            setRequestStatus(false);
        }

    }

    /**
     * Makes a call to the air quality API and returns the current air quality data.
     * 
     * @return A map containing the current air quality data.
     * @throws IOException          If there is an error in making the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    public Map<String, Object> airQualityApiCall() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(AIR_QUALITY_URL)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(jsonString, Map.class);

        return (Map<String, Object>) map.get("current");

    }

    /**
     * Sets the European AQI value.
     * 
     * @param europeanAqi The European AQI value to set.
     */
    public void setEuropeanAqi(int europeanAqi) {
        this.europeanAqi = europeanAqi;
    }

    /**
     * Gets the European AQI value.
     * 
     * @return The European AQI value.
     */
    public int getEuropeanAqi() {
        return europeanAqi;
    }

    /**
     * Sets the ozone value.
     * 
     * @param ozone The ozone value to set.
     */
    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    /**
     * Gets the ozone value.
     * 
     * @return The ozone value.
     */
    public double getOzone() {
        return ozone;
    }

    /**
     * Sets the sulphur dioxide value.
     * 
     * @param sulphurDioxide The sulphur dioxide value to set.
     */
    public void setSulphurDioxide(double sulphurDioxide) {
        this.sulphurDioxide = sulphurDioxide;
    }

    /**
     * Gets the sulphur dioxide value.
     * 
     * @return The sulphur dioxide value.
     */
    public double getSulphurDioxide() {
        return sulphurDioxide;
    }

    /**
     * Sets the nitrogen dioxide value.
     * 
     * @param nitrogenDioxide The nitrogen dioxide value to set.
     */
    public void setNitrogenDioxide(double nitrogenDioxide) {
        this.nitrogenDioxide = nitrogenDioxide;
    }

    /**
     * Gets the nitrogen dioxide value.
     * 
     * @return The nitrogen dioxide value.
     */
    public double getNitrogenDioxide() {
        return nitrogenDioxide;
    }

    /**
     * Sets the UV index value.
     * 
     * @param uvIndex The UV index value to set.
     */
    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }

    /**
     * Gets the UV index value.
     * 
     * @return The UV index value.
     */
    public double getUvIndex() {
        return uvIndex;
    }

    /**
     * Sets the request status.
     * 
     * @param requestStatus The request status to set.
     */
    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * Gets the request status.
     * 
     * @return The request status.
     */
    public boolean getRequestStatus() {
        return this.requestStatus;
    }

    /**
     * Checks the European AQI value and returns a string representing the air
     * quality.
     * 
     * @return A string representing the air quality.
     */
    public String checkEuropeanAqi() {

        double value = this.getEuropeanAqi();
        String response = "N/A";

        if (value >= 0 && value <= 20) {
            response = this.GOOD;
        } else if (value > 20 && value <= 40) {
            response = this.FAIR;
        } else if (value > 40) {
            response = this.POOR;
        }

        return response;

    }

    /**
     * Checks the ozone value and returns a string representing the air quality.
     * 
     * @return A string representing the air quality.
     */
    public String checkOzone() {

        double value = this.getOzone();
        String response = "N/A";

        if (value >= 0 && value <= 50) {
            response = this.GOOD;
        } else if (value > 50 && value <= 100) {
            response = this.FAIR;
        } else if (value > 100) {
            response = this.POOR;
        }

        return response;
    }

    /**
     * Checks the sulphur dioxide value and returns a string representing the air
     * quality.
     * 
     * @return A string representing the air quality.
     */
    public String checkSulphurDioxide() {

        double value = this.getSulphurDioxide();
        String response = "N/A";

        if (value >= 0 && value <= 100) {
            response = this.GOOD;
        } else if (value > 100 && value <= 200) {
            response = this.FAIR;
        } else if (value > 200) {
            response = this.POOR;
        }

        return response;
    }

        /**
     * Checks the UV index value and returns a string representing the air quality.
     * @return A string representing the air quality.
     */
    public String checkUVIndex() {

        double value = this.getUvIndex();
        String response = "N/A";

        if (value >= 0 && value <= 2) {
            response = this.GOOD;
        } else if (value > 2 && value <= 5) {
            response = this.FAIR;
        } else if (value > 5) {
            response = this.POOR;
        }

        return response;
    }

    /**
     * Checks the nitrogen dioxide value and returns a string representing the air quality.
     * @return A string representing the air quality.
     */
    public String checkNitrogenDioxide() {

        double value = this.getNitrogenDioxide();
        String response = "N/A";

        if (value >= 0 && value <= 50) {
            response = this.GOOD;
        } else if (value > 50 && value <= 100) {
            response = this.FAIR;
        } else if (value > 100) {
            response = this.POOR;
        }

        return response;
    }

}