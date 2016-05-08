package assignment11;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class Assignment10 extends Application {

    private static final String API_ENDPOINT = "http://xml.buienradar.nl/";
    private static final String STATION_TAG = "weerstation";
    private static final int GUTTER = 10;

    private final Map<String, WeatherInfo> stationInfo = new HashMap<>();
    private DocumentBuilder builder;

    @Override
    public void start(Stage primaryStage) throws ParseException, SAXException, IOException, ParserConfigurationException {
        builder = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
        refreshData();

        ChoiceBox<String> stationChoice = buildStationChoice("Meetstation Arcen");

        ImageView icon = new ImageView();
        icon.setImage(new Image(stationInfo.get(stationChoice.getValue()).getIconURL()));

        Text weatherText = new Text(stationInfo.get(stationChoice.getValue()).toString());

        stationChoice.setOnAction(e -> {
            WeatherInfo info = stationInfo.get(stationChoice.getValue());
            weatherText.setText(info.toString());
            icon.setImage(new Image(info.getIconURL()));
        });

        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh Data");
        btnRefresh.setOnAction(e -> {
            try {
                refreshData();
            } catch (ParseException | SAXException | IOException ex) {
                Logger.getLogger(Assignment10.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(GUTTER);
        root.setPadding(new Insets(GUTTER));
        root.getChildren().addAll(
                icon,
                weatherText,
                stationChoice,
                btnRefresh,
                new Hyperlink("buieneradar.nl")
        );

        Scene scene = new Scene(root);

        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Refresh weather data.
     */
    private void refreshData() throws ParseException, SAXException, IOException {
        NodeList stationNodes = builder.parse(new URL(API_ENDPOINT).openStream()).getElementsByTagName(STATION_TAG);
        stationInfo.clear();
        WeatherInfo info;
        for (int i = 0; i < stationNodes.getLength(); i++) {
            info = new WeatherInfo((Element) stationNodes.item(i));
            stationInfo.put(info.getStationName(), info);
        }
    }

    /**
     * @return a choice box with all weather stations
     */
    public ChoiceBox<String> buildStationChoice(String initial) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        stationInfo.keySet().stream().forEach(s -> choiceBox.getItems().add(s));
        choiceBox.setValue(initial);
        return choiceBox;
    }

}
