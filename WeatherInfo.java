package assignment11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;

/**
 * Hold information about the weather at a station.
 *
 * @author Hendrik Werner // s4549775
 */
public class WeatherInfo {

    private static final String STATION_NAME_TAG = "stationnaam";
    private static final String DATE_TAG = "datum";
    private static final String HUMIDITY_TAG = "luchtvochtigheid";
    private static final String TEMP_TAG = "temperatuurGC";
    private static final String WIND_SPEED_TAG = "windsnelheidMS";
    private static final String WIND_DIRECTION_TAG = "windrichting";
    private static final String GUSTS_TAG = "windstotenMS";
    private static final String AIR_PRESSURE_TAG = "luchtdruk";
    private static final String VISIBILITY_TAG = "zichtmeters";
    private static final String RAIN_TAG = "regenMMPU";
    private static final String ICON_URL_TAG = "icoonactueel";
    private static final String DATE_FORMAT_XML = "MM/dd/yyyy HH:mm:ss";

    private final Date date;
    private final String stationName;
    private final String windDirection;
    private final String visibility;
    private final String airPressure;
    private final String rain;
    private final String humidity;
    private final String temp;
    private final String windSpeed;
    private final String gusts;
    private final String iconURL;

    /**
     * @param station the dom element describing the station
     */
    public WeatherInfo(Element station) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_XML);
        date = dateFormat.parse(station.getElementsByTagName(DATE_TAG).item(0).getTextContent());
        stationName = station.getElementsByTagName(STATION_NAME_TAG).item(0).getTextContent();
        humidity = station.getElementsByTagName(HUMIDITY_TAG).item(0).getTextContent();
        temp = station.getElementsByTagName(TEMP_TAG).item(0).getTextContent();
        windSpeed = station.getElementsByTagName(WIND_SPEED_TAG).item(0).getTextContent();
        gusts = station.getElementsByTagName(GUSTS_TAG).item(0).getTextContent();
        airPressure = station.getElementsByTagName(AIR_PRESSURE_TAG).item(0).getTextContent();
        windDirection = station.getElementsByTagName(WIND_DIRECTION_TAG).item(0).getTextContent();
        visibility = station.getElementsByTagName(VISIBILITY_TAG).item(0).getTextContent();
        rain = station.getElementsByTagName(RAIN_TAG).item(0).getTextContent();
        iconURL = station.getElementsByTagName(ICON_URL_TAG).item(0).getTextContent();
    }

    /**
     * @return the stationName
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the windDirection
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * @return the humidity
     */
    public String getHumidity() {
        return humidity + "%";
    }

    /**
     * @return the temp
     */
    public String getTemp() {
        return temp + "°C";
    }

    /**
     * @return the windSpeed
     */
    public String getWindSpeed() {
        return windSpeed + "m/s";
    }

    /**
     * @return the gusts
     */
    public String getGusts() {
        return gusts + "m/s";
    }

    /**
     * @return the airPressure
     */
    public String getAirPressure() {
        return airPressure + "hPa";
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility + "m";
    }

    /**
     * @return the rain
     */
    public String getRain() {
        return rain + "mmpu";
    }

    /**
     * @return the iconURL
     */
    public String getIconURL() {
        return iconURL;
    }

    /**
     * @return a human-readable string representation
     */
    @Override
    public String toString() {
        return String.join("\n",
                "Station: " + getStationName(),
                "Date: " + getDate(),
                "Temperature: " + getTemp(),
                "Rain: " + getRain(),
                "Wind Speed: " + getWindSpeed(),
                "Wind Direction: " + getWindDirection(),
                "Gusts: " + getGusts(),
                "Air Pressure: " + getAirPressure(),
                "Humidity: " + getHumidity(),
                "Visibility: " + getVisibility()
        );
    }

}
