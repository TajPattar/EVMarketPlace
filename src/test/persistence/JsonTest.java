package persistence;

import model.EV;
import static org.junit.jupiter.api.Assertions.assertEquals;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java


// Tester for JsonTest
public class JsonTest {
    protected void checkEV(String make, String model, int year, String colour, String status, int mileage, int range,
                           int chargingcost, int rebate, int price, int chargerinstallcost, EV ev) {
        assertEquals(make, ev.getMake());
        assertEquals(model, ev.getModel());
        assertEquals(year, ev.getYear());
        assertEquals(colour, ev.getColour());
        assertEquals(status, ev.getStatus());
        assertEquals(mileage, ev.getMileage());
        assertEquals(range, ev.getRange());
        assertEquals(chargingcost, ev.getChargingcost());
        assertEquals(rebate, ev.getRebate());
        assertEquals(price, ev.getPrice());
        assertEquals(chargerinstallcost, ev.getChargerinstallcost());



    }
}
