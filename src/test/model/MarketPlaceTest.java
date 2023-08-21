package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MarketPlaceTest {
    private MarketPlace emptymarketplace;
    private EV Audi;
    private EV Tesla;
    private EV Ford;
    private EV TeslaUsed;

    @BeforeEach
    public void runbefore() {
        emptymarketplace = new MarketPlace();

        Audi = new EV("Audi", "Q8-Etron", 2024, "white", "new",30, 459,
                6,0,104400, 1000 );
        Tesla = new EV("Tesla", "Model 3", 2023, "grey", "new",0, 438,
                3,9000,56990, 650 );
        Ford = new EV("Ford", "Mustang Mach-E", 2021, "blue", "used",27600,
                397,6, 0,39000, 820 );
        TeslaUsed = new EV("Tesla", "Model Y", 2023, "red", "used",100000,
                438, 3,0,37000, 650 );
    }

    @Test
    public void testConstructor() {
       assertFalse((emptymarketplace.checkIfEVModelInList(Audi)));
       assertFalse((emptymarketplace.checkIfEVModelInList(Tesla)));
       assertFalse((emptymarketplace.checkIfEVModelInList(Ford)));
       assertFalse((emptymarketplace.checkIfEVModelInList(TeslaUsed)));
       assertEquals(emptymarketplace.getVehicles().size(), 0);
        assertEquals(emptymarketplace.getModellist().size(), 0);
    }

    @Test
    public void testListEv() {
        emptymarketplace.listEV(Audi);
        assertTrue(emptymarketplace.checkIfEVModelInList(Audi));
        assertTrue(emptymarketplace.getVehicles().contains(Audi));
        emptymarketplace.listEV(Tesla);
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        assertFalse(emptymarketplace.getVehicles().contains(Ford));
        assertTrue(emptymarketplace.getVehicles().contains(Tesla));
        assertTrue(emptymarketplace.checkIfEVModelInList(Tesla));

    }

    @Test
    public void testRemoveEV() {
        emptymarketplace.listEV(Audi);
        assertTrue(emptymarketplace.checkIfEVModelInList(Audi));
        emptymarketplace.removeEV(Audi);
        assertFalse(emptymarketplace.checkIfEVModelInList(Audi));
        emptymarketplace.listEV(Ford);
        emptymarketplace.listEV(Tesla);
        assertTrue(emptymarketplace.checkIfEVModelInList(Ford));
        assertTrue(emptymarketplace.checkIfEVModelInList(Tesla));
        emptymarketplace.removeEV(Ford);
        emptymarketplace.removeEV(Tesla);
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        assertFalse(emptymarketplace.checkIfEVModelInList(Tesla));
    }

    @Test
    public void testRemoveEVOneRemovedNotExistInMarketPlace() {
        emptymarketplace.removeEV(Ford);
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        emptymarketplace.listEV(Tesla);
        emptymarketplace.listEV(TeslaUsed);
        emptymarketplace.removeEV(Audi);
        assertFalse(emptymarketplace.checkIfEVModelInList(Audi));
        assertTrue(emptymarketplace.checkIfEVModelInList(Tesla));
        assertTrue(emptymarketplace.checkIfEVModelInList(TeslaUsed));
    }


    @Test
    public void testNewEVOnly() {
        emptymarketplace.listEV(Audi);
        emptymarketplace.listEV(Ford);
        emptymarketplace.newEVOnly();
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        assertTrue(emptymarketplace.checkIfEVModelInList(Audi));
    }

    @Test
    public void testNewEVonlymultipleoldcarremoved() {
        emptymarketplace.listEV(Audi);
        emptymarketplace.listEV(Ford);
        emptymarketplace.listEV(Tesla);
        emptymarketplace.listEV(TeslaUsed);
        emptymarketplace.newEVOnly();
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        assertFalse(emptymarketplace.checkIfEVModelInList(TeslaUsed));
        assertTrue(emptymarketplace.checkIfEVModelInList(Tesla));
        assertTrue(emptymarketplace.checkIfEVModelInList(Audi));
    }

    @Test
    public void testNewEVOnlyNonNewCars() {
        assertFalse(emptymarketplace.checkIfEVModelInList(Audi));
        emptymarketplace.listEV(TeslaUsed);
        emptymarketplace.listEV(Ford);
        emptymarketplace.newEVOnly();
        assertFalse(emptymarketplace.checkIfEVModelInList(Ford));
        assertFalse(emptymarketplace.checkIfEVModelInList(TeslaUsed));


    }

    @Test
    public void testShowAllEV() {
        emptymarketplace.listEV(Audi);
        emptymarketplace.listEV(Ford);
        emptymarketplace.listEV(Tesla);
        emptymarketplace.showAllEV();
        assertTrue(emptymarketplace.checkIfEVModelInList(Audi));
        assertTrue(emptymarketplace.checkIfEVModelInList(Ford));
        assertTrue(emptymarketplace.checkIfEVModelInList(Tesla));
    }

    @Test
    public void testTotalListing() {
        assertEquals(0, emptymarketplace.totalListings());
        emptymarketplace.listEV(Audi);
        assertEquals(1, emptymarketplace.totalListings());
        emptymarketplace.listEV(Ford);
        emptymarketplace.listEV(Tesla);
        assertEquals(3, emptymarketplace.totalListings());
        emptymarketplace.removeEV(Tesla);
        assertEquals(2, emptymarketplace.totalListings());
    }
}
