package Wojna;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SoldierTest {
    @Test
    public void testPromoteSoldier() {
        Soldier soldier = new Soldier("Private", 4);
        soldier.increaseExperience();  // experience = 5, should promote
        assertEquals("Corporal", soldier.getRank());
    }
    
    @Test
    public void testStrengthCalculation() {
        Soldier soldier = new Soldier("Private", 3);
        assertEquals(3, soldier.calculateStrength());
    }
}

