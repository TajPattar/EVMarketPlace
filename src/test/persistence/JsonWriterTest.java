package persistence;

import model.EV;
import model.MarketPlace;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java

// Testing JsonWriter
public class JsonWriterTest extends  JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            MarketPlace mp = new MarketPlace();
            JsonWriter jsonwriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonwriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyMarketPlace() {
        try {
            MarketPlace mp = new MarketPlace();
            JsonWriter jsonwriter = new JsonWriter("./data/testWriterEmptyMarketPlace.json");
            jsonwriter.open();
            jsonwriter.write(mp);
            jsonwriter.close();

            JsonReader jsonreader = new JsonReader("./data/testWriterEmptyMarketPlace.json");
            mp = jsonreader.read();
            assertEquals(0, mp.totalListings());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterMarketPlaceWithListing() {
        try {
            MarketPlace mp = new MarketPlace();
            EV Tesla = new EV("Tesla", "Model 3", 2023, "grey", "new",0,
                    438,
                    3,9000,56990, 650 );
            mp.listEV(Tesla);
            JsonWriter jsonwriter = new JsonWriter("./data/testWriterMarketPlaceWithListing.json");
            jsonwriter.open();
            jsonwriter.write(mp);
            jsonwriter.close();


            JsonReader jsonreader = new JsonReader("./data/testWriterMarketPlaceWithListing.json");
            mp = jsonreader.read();
            assertEquals(1, mp.totalListings());
            List<EV> evs = mp.getVehicles();
            checkEV("Tesla", "Model 3", 2023, "grey", "new",0, 438,
                    3,9000,56990, 650, evs.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
