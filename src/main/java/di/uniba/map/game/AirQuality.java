package di.uniba.map.game;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class AirQuality {

    private final String AIR_QUALITY_URL = "https://air-quality-api.open-meteo.com/v1/air-quality?latitude=41.1207&longitude=16.8698&current=european_aqi,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone,uv_index";

    private final String GOOD = "BUONO";
    private final String FAIR = "MEDIO";
    private final String POOR = "BASSO";

    private int europeanAqi;
    private double ozone;
    private double sulphurDioxide;
    private double nitrogenDioxide;
    private double uvIndex;
    private boolean requestStatus;

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

    public Map<String, Object> airQualityApiCall() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(AIR_QUALITY_URL)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(jsonString, Map.class);

        return (Map<String, Object>) map.get("current");

    }

    public void setEuropeanAqi(int europeanAqi) {
        this.europeanAqi = europeanAqi;
    }

    public int getEuropeanAqi() {
        return europeanAqi;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public double getOzone() {
        return ozone;
    }

    public void setSulphurDioxide(double sulphurDioxide) {
        this.sulphurDioxide = sulphurDioxide;
    }

    public double getSulphurDioxide() {
        return sulphurDioxide;
    }

    public void setNitrogenDioxide(double nitrogenDioxide) {
        this.nitrogenDioxide = nitrogenDioxide;
    }

    public double getNitrogenDioxide() {
        return nitrogenDioxide;
    }

    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean getRequestStatus(){
        return this.requestStatus;
    }

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

    public String checkNitrogenDioxide() {

        double value = this.getNitrogenDioxide();
        String response = "N/A";

        if (value >= 0 && value <= 40) {
            response = this.GOOD;
        } else if (value > 40 && value <= 90) {
            response = this.FAIR;
        } else if (value > 90) {
            response = this.POOR;
        }

        return response;
    }

    public String checkUvIndex() {

        double value = this.getUvIndex();
        String response = "N/A";

        if (value >= 0 && value <= 2) {
            response = this.GOOD;
        } else if (value > 2 && value <= 7) {
            response = this.FAIR;
        } else if (value > 7) {
            response = this.POOR;
        }

        return response;
    }

}
