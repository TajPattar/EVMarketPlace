package persistence;

import model.EV;
import model.MarketPlace;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.json.*;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java

// Represents a reader that will read the workroom from JSON data
// that will be stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructor for reader that will read from the source files
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the MarketPlace from the file and then returns it
    // If error occurs when reading the data from file it then throws IOEXCEPTION
    public MarketPlace read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMarketPlace(jsonObject);

    }

    // EFFECTS: reads source file as a string and will then return it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MarketPlace from JSON object and will then return it
    private MarketPlace parseMarketPlace(JSONObject jsonObject) {
        MarketPlace mp = new MarketPlace();
        addEVs(mp, jsonObject);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses EV's from JSON object and adds them to MarketPlace
    private void addEVs(MarketPlace mp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("vehicles");
        for (Object json : jsonArray) {
            JSONObject nextEV = (JSONObject) json;
            addEV(mp, nextEV);
        }
    }

    // MODIFIES: mp
    // EFFECTS: parses EV from JSON object and adds to marketplace
    private void addEV(MarketPlace mp, JSONObject jsonObject) {
        String make = jsonObject.getString("make");
        String model = jsonObject.getString("model");
        int year = jsonObject.getInt("year");
        String colour = jsonObject.getString("colour");
        String status = jsonObject.getString("status");
        int mileage = jsonObject.getInt("mileage");
        int range = jsonObject.getInt("range");
        int chargingcost = jsonObject.getInt("chargingcost");
        int rebate = jsonObject.getInt("rebate");
        int price = jsonObject.getInt("price");
        int chargerinstallcost = jsonObject.getInt("chargerinstallcost");
        EV ev = new EV(make,model,year,colour, status, mileage, range, chargingcost, rebate, price, chargerinstallcost);
        mp.listEV(ev);
    }






}
