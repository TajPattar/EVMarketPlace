package ui;


import java.util.Scanner;

import model.Event;
import model.EventLog;
import model.MarketPlace;
import model.EV;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

// Online MarketPlace for EV's
public class MarketPlaceApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private MarketPlace marketplace;
    private Scanner input;
    private EV ev;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the MarketPlaceApp
    public MarketPlaceApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        marketplace = new MarketPlace();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMarketPlace();
    }


    //MODIFIES: this
    //EFFECTS: Will process the user input
    private void runMarketPlace() {
        boolean continueBrowsing = true;
        String command = null;
        input = new Scanner(System.in);

        init();


        while (continueBrowsing) {
            displayMarketPlace();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continueBrowsing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for visiting!");
        for (model.Event event: model.EventLog.getInstance()) {
            System.out.println(event);
        }


    }

    // MODIFIES: this
    // EFFECTS: processes the commands from the user
    private void processCommand(String command) {
        if (command.equals("l")) {
            doListEV();
        } else if (command.equals("p")) {
            doRemoveEV(ev);
        } else if (command.equals("n")) {
            doNewEVOnly();
        } else if (command.equals("a")) {
            doShowAllEV();
        } else if (command.equals("t")) {
            doTotalListings();
        } else if (command.equals("s")) {
            saveMarketPlace();
        } else if (command.equals("i")) {
            loadMarketPlace();
        } else {
            System.out.println("Not a valid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the MarketPlace
    private void init() {
        marketplace = new MarketPlace();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


     //EFFECTS: displays marketplace menu with options for user to select
    private void displayMarketPlace() {
        System.out.println("\nWelcome, please select from following options:");
        System.out.println("\tl -> List your EV for sale");
        System.out.println("\tp -> My EV has been purchased!");
        System.out.println("\tn -> NEW EV only mode: list only new EV models");
        System.out.println("\ta -> See all EV models'");
        System.out.println("\tt -> Total amount of EV's for sale");
        System.out.println("\ts -> save market place to file");
        System.out.println("\ti -> load market place to file");
        System.out.println("\tq -> Leave App");
    }

     // MODIFIES: this
    // EFFECTS; gets information about EV from user and then adds EV to marketplace
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void doListEV() {
        String make;
        String model;
        int year;
        String colour;
        String status;
        int mileage;
        int range;
        int chargingcost;
        int rebate;
        int price;
        int chargerinstallcost;


        System.out.println("What is the make of the EV you want to sell?");
        make = input.next();
        System.out.println("Model of the EV?");
        model = input.next();
        System.out.println("Year of the EV?");
        year = input.nextInt();
        System.out.println("Colour of the EV?");
        colour = input.next();
        System.out.println("Status of the EV (new or used?)");
        status = input.next();
        System.out.println("mileage of the EV (in km)");
        mileage = input.nextInt();
        System.out.println("range of the EV (in km)");
        range = input.nextInt();
        System.out.println("Approximate cost to fully charge the EV (nearest $)?");
        chargingcost = input.nextInt();
        System.out.println("Max combined provincial and federal rebates ($)? ");
        rebate = input.nextInt();
        System.out.println("What price would you like to list the EV?");
        price = input.nextInt();
        System.out.println("How about the cost to have a home charger installed ($)?");
        chargerinstallcost = input.nextInt();

        ev = new EV(make, model, year, colour, status, mileage, range,
                 chargingcost, rebate, price, chargerinstallcost);
        System.out.println("Congrats, your EV has been added to marketplace");
        marketplace.listEV(ev);
    }

    // MODIFIES: this
    // EFFECTS: Removes the last added EV from Marketplace when sold
    private void doRemoveEV(EV ev) {
        marketplace.removeEV(ev);
        System.out.println("Congrats on the sale, your listing has been removed");


    }

    // MODIFIES: this
    // Produces a list of all new status EV in marketplace
    private void doNewEVOnly() {
        marketplace.newEVOnly();
        System.out.println(marketplace.getModellist());




    }

    // Produces a list of all EV in Marketplace
    private void doShowAllEV() {
        marketplace.showAllEV();
        System.out.println(marketplace.getModellist());
    }

    // Produces the total amount of EV for sale in the marketplace
    public void doTotalListings() {
        marketplace.totalListings();
        System.out.println(marketplace.totalListings());
    }

    // The following code was taken / made with guidance from
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

    // EFFECTS: saves marketplace to file
    private void saveMarketPlace() {
        try {
            jsonWriter.open();
            jsonWriter.write(marketplace);
            jsonWriter.close();
            System.out.println("Saved listings to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads MarketPlace from file
    private void loadMarketPlace() {
        try {
            marketplace = jsonReader.read();
            System.out.println("Loaded previous listings from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file " + JSON_STORE);
        }
    }


}





