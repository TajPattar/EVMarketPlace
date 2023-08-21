package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// A Marketplace where all EV's are listed and can be sold
public class MarketPlace implements Writable {

    //fields that include a list of EV's on the marketplace and list of EV model names in String form
    private ArrayList<EV> vehicles;
    private ArrayList<String> modellist;



    // EFFECT: Constructor the MarketPlace where list of EV's and a list of EV models is listed
    // Both list start off empty
    public MarketPlace() {
        this.vehicles = new ArrayList<EV>();
        this.modellist = new ArrayList<String>();

    }

    public ArrayList<EV> getVehicles() {
        return vehicles;
    }

    public ArrayList<String> getModellist() {
        return modellist;
    }

    // MODIFIES: this
    // EFFECTS: lists/adds an EV to the current list of EV's and lists/adds an EV model name to current
    // list of EV model names
    public void listEV(EV ev) {
        this.vehicles.add(ev);
        this.modellist.add(ev.getModel());
        EventLog.getInstance().logEvent(new Event("Listed " + ev.getModel() + " to MarketPlace"));
    }

    //EFFECTS: produces true if ev model is in the list of models in marketplace, false otherwise
    public boolean checkIfEVModelInList(EV ev) {
        return this.modellist.contains(ev.getModel());

    }



    // MODIFIES: this
    // EFFECTS: Removes EV from current list of EV if it exists in Marketplace, otherwise just stays as is
    public void removeEV(EV ev) {
        if (this.vehicles.contains(ev)) {
            this.vehicles.remove(ev);
            this.modellist.remove((ev.getModel()));
            EventLog.getInstance().logEvent(new Event("Removed " + ev.getModel() + " from MarketPlace"));
        }
    }


// The following code was inspired from/ learned from a tutorial on how to filter lists in Java
// https://techndeck.com/how-to-filter-a-list-in-java-unique-ways-to-filter-arraylist/
    //MODIFIES: this
    //EFFECTS: returns a list of EV model names that have a "new" status only
    public void newEVOnly() {
        ArrayList<String> updatedMarketPlace = new ArrayList<String>();
        for  (EV ev: this.vehicles) {
            if (ev.getStatus().equals("new")) {
                updatedMarketPlace.add(ev.getModel());
            }
        }
        this.modellist = updatedMarketPlace;
        EventLog.getInstance().logEvent(new Event("NEW EV ONLY MODE: only showing new EV's"));

    }



    // EFFECTS: produces list of all EV model name in marketplace
    public ArrayList<String> showAllEV() {
        EventLog.getInstance().logEvent(new Event("Showing all EV models"));
        return this.modellist;

    }

    // EFFECTS: produces a count of how many EV models are listed in marketplace
    public int totalListings() {
        EventLog.getInstance().logEvent(new Event("There is " + this.modellist.size() + " EV's for sale"));
        return this.modellist.size();
    }


    // The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java

    @Override
    // EFFECTS: writes the JSON representation of the MarketPlace to file
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("vehicles", vehiclesToJson());
        json.put("modellist", modellist);
        return json;
    }

    // EFFECTS: returns things in this MarketPlace as a JSON array
    private JSONArray vehiclesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (EV ev : vehicles) {
            jsonArray.put(ev.toJson());
        }

        return jsonArray;
    }
}
