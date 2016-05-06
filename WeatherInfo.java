package assignment11;

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
    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private final SimpleDateFormat dateFormat;
    private final String stationName;
    private final Date date;
    private final String windDirection;
    private final int humidity;
    private final int visibility;
    private final int rain;
    private final double temp;
    private final double windSpeed;
    private final double gusts;
    private final double airPressure;

    /**
     * @param station the dom element describing the station
     */
    public WeatherInfo(Element station) throws Exception {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        stationName = station.getElementsByTagName(STATION_NAME_TAG).item(0).getTextContent();
        date = dateFormat.parse(station.getElementsByTagName(DATE_TAG).item(0).getTextContent());
        humidity = Integer.parseInt(station.getElementsByTagName(HUMIDITY_TAG).item(0).getTextContent());
        temp = Double.parseDouble(station.getElementsByTagName(TEMP_TAG).item(0).getTextContent());
        windSpeed = Double.parseDouble(station.getElementsByTagName(WIND_SPEED_TAG).item(0).getTextContent());
        gusts = Double.parseDouble(station.getElementsByTagName(GUSTS_TAG).item(0).getTextContent());
        airPressure = Double.parseDouble(station.getElementsByTagName(AIR_PRESSURE_TAG).item(0).getTextContent());
        windDirection = station.getElementsByTagName(WIND_DIRECTION_TAG).item(0).getTextContent();
        visibility = Integer.parseInt(station.getElementsByTagName(VISIBILITY_TAG).item(0).getTextContent());
        rain = Integer.parseInt(station.getElementsByTagName(RAIN_TAG).item(0).getTextContent());
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
    public int getHumidity() {
        return humidity;
    }

    /**
     * @return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @return the windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @return the gusts
     */
    public double getGusts() {
        return gusts;
    }

    /**
     * @return the airPressure
     */
    public double getAirPressure() {
        return airPressure;
    }

    /**
     * @return the visibility
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * @return the rain
     */
    public int getRain() {
        return rain;
    }

}
