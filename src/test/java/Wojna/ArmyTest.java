package Wojna;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArmyTest {
    @Test
    public void AddSoldiers(){
        Army sut = new Army();
        sut.addSoldier(new Soldier("Private",1));
        sut.addSoldier(new Soldier("Private",1));
        sut.addSoldier(new Soldier("Private",1));
        int result = sut.getArmy().size();
        assertEquals(3, result);
    }
}
