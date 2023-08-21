package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Electric Vehicle and the Information related to the Electric Vehicle
public class EV implements Writable {

    // Fields that represent important information about the EV
    private String make;
    private String model;
    private int year;
    private String colour;
    private String status;
    private int mileage;
    private int range;
    private int chargingcost;
    private int rebate;
    private int price;
    private int chargerinstallcost;

    // EFFECTS: Constructs an Electric Vehicle with it's make (String), model (String), year (int), colour (String),
    // status (String), mileage(in km),
    // range(in km), charging cost (rounded to nearest dollar),
    // rebates (dollar, includes both Federal and Provincial rebate),
    // price (dollar), and the price for having a home charger installed (dollar)
    public EV(String make, String model, int year, String colour, String status, int mileage, int range,
              int chargingcost, int rebate, int price, int chargerinstallcost) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.colour = colour;
        this.status = status;
        this.mileage = mileage;
        this.range = range;
        this.chargingcost = chargingcost;
        this.rebate = rebate;
        this.price = price;
        this.chargerinstallcost = chargerinstallcost;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColour() {
        return colour;
    }

    public String getStatus() {
        return status;
    }

    public int getMileage() {
        return mileage;
    }

    public int getRange() {
        return range;
    }

    public int getChargingcost() {
        return chargingcost;
    }

    public int getRebate() {
        return rebate;
    }

    public int getPrice() {
        return price;
    }

    public int getChargerinstallcost() {
        return chargerinstallcost;
    }

    // The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/Thingy.java

    @Override
    // EFFECTS: writes the JSON representation of the MarketPlace to file
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("make", make);
        json.put("model", model);
        json.put("year", year);
        json.put("colour", colour);
        json.put("status", status);
        json.put("mileage", mileage);
        json.put("range", range);
        json.put("chargingcost", chargingcost);
        json.put("rebate", rebate);
        json.put("price", price);
        json.put("chargerinstallcost", chargerinstallcost);
        return json;
    }


}
