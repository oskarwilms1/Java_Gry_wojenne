package Wojna;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SoldierTest {
    @Test
    public void testPromoteSoldier() {
        Soldier soldier = new Soldier("Private",1);
        soldier.increaseExperience();  // experience = 5, should promote
        soldier.increaseExperience();
        soldier.increaseExperience();
        soldier.increaseExperience();
        soldier.increaseExperience();
        assertEquals("Corporal", soldier.getRank());
    }
    
    @Test
    public void testStrengthCalculation() {
        Soldier soldier = new Soldier("Private",4);
        assertEquals(4, soldier.calculateStrength());
    }
}

