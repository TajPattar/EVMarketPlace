package persistence;

import model.EV;
import model.MarketPlace;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;


// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java


// Testing for JsonReader
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderFileNoExist() {
        JsonReader jsonreader = new JsonReader("./data/noSuchFile.json");
        try {
            MarketPlace mp = jsonreader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyMarketPlace() {
        JsonReader jsonreader = new JsonReader("./data/testReaderEmptyMarketPlace.json");
        try {
            MarketPlace mp = jsonreader.read();
            assertEquals(0, mp.totalListings());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderInMarketPlaceWithListing() {
        JsonReader jsonreader = new JsonReader("./data/testReaderMarketPlaceWithListing.json");
        try {
            EV Tesla = new EV("Tesla", "Model 3", 2023, "grey", "new",0,
                    438,
                    3,9000,56990, 650 );
            MarketPlace mpp = jsonreader.read();
            assertEquals(1, mpp.totalListings());
            List<EV> evs = mpp.getVehicles();
           assertTrue(mpp.checkIfEVModelInList(Tesla));
            checkEV("Tesla", "Model 3", 2023, "grey", "new",0, 438,
                    3,9000,56990, 650, evs.get(0));



        } catch (IOException e) {
            fail("Couldn't read from file");

        }

    }
}
