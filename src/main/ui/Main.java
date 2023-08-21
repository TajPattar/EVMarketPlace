package ui;

import java.io.FileNotFoundException;
import model.EventLog;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/Main.java

public class Main {
    public static void main(String[] args) {
        try {
            new MarketPlaceApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: the file is unable to be found");
        }

    }


}
