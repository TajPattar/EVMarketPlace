package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EVTest {
    private EV Audi;
    private EV Tesla;
    private EV Ford;

    @BeforeEach
    public void runBefore() {
        Audi = new EV("Audi", "Q8-Etron", 2024, "white", "new",30, 459,
                6,0,104400, 1000 );
        Tesla = new EV("Tesla", "Model 3", 2023, "grey", "new",0, 438,
                3,9000,56990, 650 );
        Ford = new EV("Ford", "Mustang Mach-E", 2021, "blue", "used",27600,
                397,6, 0,39000, 820 );

    }

    @Test
    public void testConstructorAudi() {
        assertEquals(Audi.getMake(), "Audi");
        assertEquals(Audi.getModel(), "Q8-Etron");
        assertEquals(Audi.getYear(), 2024);
        assertEquals(Audi.getColour(), "white");
        assertEquals(Audi.getStatus(), "new");
        assertEquals(Audi.getMileage(), 30);
        assertEquals(Audi.getRange(), 459);
        assertEquals(Audi.getChargingcost(), 6);
        assertEquals(Audi.getRebate(), 0);
        assertEquals(Audi.getPrice(), 104400);
        assertEquals(Audi.getChargerinstallcost(), 1000);
    }

    @Test
    public void testConstructorFord() {
        assertEquals(Ford.getMake(), "Ford");
        assertEquals(Ford.getModel(), "Mustang Mach-E");
        assertEquals(Ford.getYear(), 2021);
        assertEquals(Ford.getColour(), "blue");
        assertEquals(Ford.getStatus(), "used");
        assertEquals(Ford.getMileage(), 27600);
        assertEquals(Ford.getRange(), 397);
        assertEquals(Ford.getChargingcost(), 6);
        assertEquals(Ford.getRebate(), 0);
        assertEquals(Ford.getPrice(), 39000);
        assertEquals(Ford.getChargerinstallcost(), 820);
    }

    @Test
    public void testConstructorGetRebateover0() {
        assertEquals(Tesla.getRebate(), 9000);

    }


}