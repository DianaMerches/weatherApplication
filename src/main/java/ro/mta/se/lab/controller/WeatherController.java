package ro.mta.se.lab.controller;

import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.model.WeatherModel;

import javax.management.modelmbean.ModelMBean;

public class WeatherController {

    @FXML
    private ComboBox<String> cityID;

    @FXML
    private ComboBox<String> countryID;
    WeatherModel Country;

    @FXML
    private Label mainID;

    @FXML
    private Label tempID;

    @FXML
    private Label humidityID;

    @FXML
    private Label pressureID;

    @FXML
    private Label windID;

    @FXML
    public void initialize() {
        Country = new WeatherModel();
        ReadFromFile();
        setbox();
    }

    public WeatherController() {
    }


    public void setbox(){
        ObservableList<String> countryList= FXCollections.observableArrayList();
        for(int i=0;i<Country.getCountry().size();i++){
            countryList.addAll(Country.getCountry().get(i).getName());
        }
        ObservableList<String> finalList= FXCollections.observableArrayList();
        for(int i=0;i<countryList.size();i++) {
            if(!finalList.isEmpty()) {
                boolean ok=false;
                for(int j=0;j<finalList.size();j++){
                    if(finalList.get(j).equals(countryList.get(i))){
                        ok=true;
                        break;
                    }
                }
                if (!ok){
                    finalList.add(countryList.get(i));
                }
            }
            else{
                finalList.add(countryList.get(i));
            }
        }
        countryID.setItems(finalList);


    }
    public void getWeatherAPI(String cityName) {
        try {
            StringBuilder stringResultJSON = new StringBuilder();
            String API_KEY = "614114e6ec36ec1dbf433f8c21881e3c";
            String nameCityURL = cityName;
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + nameCityURL + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringResultJSON.append(line);
            }
            bufferedReader.close();

            JsonArray weatherItems = Json.parse(String.valueOf(stringResultJSON)).asObject().get("weather").asArray();
            for (JsonValue i : weatherItems) {
                String mainItem = i.asObject().getString("main", "Unknown Item");
                mainItem="General description: "+mainItem;
                mainID.setText(mainItem);
            }
            JsonObject temperature=Json.parse(String.valueOf(stringResultJSON)).asObject().get("main").asObject();
            double valueTemperature = temperature.getDouble("temp", 0);
            String tempString="Temperature: "+ valueTemperature+"°";
            tempID.setText(tempString);

            JsonObject humidity=Json.parse(String.valueOf(stringResultJSON)).asObject().get("main").asObject();
            int humidityItem = humidity.getInt("humidity", 0);
            String humidityString="Humidity: "+humidityItem+"%";
            humidityID.setText(humidityString);

            JsonObject pressure=Json.parse(String.valueOf(stringResultJSON)).asObject().get("main").asObject();
            int pressureItem = pressure.getInt("pressure", 0);
            String pressureString="Pressure: "+pressureItem;
            pressureID.setText(pressureString);

            JsonObject wind=Json.parse(String.valueOf(stringResultJSON)).asObject().get("wind").asObject();
            double valueWind=wind.getDouble("speed",0);
            String windString="Wind: "+valueWind+"%";
            windID.setText(windString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ReadFromFile(){
        try {
            File myObj = new File("in.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                var x=data.split(" ");
                Country countryName=new Country();
                countryName.setCity(x[1]);
                countryName.setLat(x[2]);
                countryName.setLon(x[3]);
                countryName.setName(x[4]);
                Country.addCountry(countryName);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void setCityInterface(ActionEvent actionEvent) {
        if(!countryID.getSelectionModel().isEmpty()){
            ObservableList<String> city= FXCollections.observableArrayList();
            String tara=countryID.getValue().toString();
            for(int i=0;i<Country.getCountry().size();i++){
                if(Country.getCountry().get(i).getName().get(0).equals(tara)){
                    for(int j=0;j<Country.getCountry().get(i).getCity().size();j++){
                        city.addAll(Country.getCountry().get(i).getCity());
                    }
                }
            }
            cityID.setItems(city);
        }
    }
    public void getDetailsInterface(ActionEvent actionEvent) {
        if(!cityID.getSelectionModel().isEmpty()){
            String city=cityID.getValue().toString();
            for(int i=0;i<Country.getCountry().size();i++){
                for(int j=0;j<Country.getCountry().get(i).getCity().size();j++){
                    if(Country.getCountry().get(i).getCity().get(j).equals(city)){
                        getWeatherAPI(city);
                    }
                }
            }
        }
    }
}
