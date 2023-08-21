package persistence;

import model.MarketPlace;
import org.json.JSONObject;

import java.io.*;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonWriter.java

// A writer that writes JSON representation of MarketPlace to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs the writer that will write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: open writer; throws FileNotFoundException if destination file
    // is unable to be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of the MarketPlace to file
    public void write(MarketPlace mp) {
        JSONObject json = mp.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes thewriter
    public void  close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file/ saves file
    private void saveToFile(String json) {
        writer.print(json);
    }





}