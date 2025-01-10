package Wojna;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeneralTest {
    @Test
    public void SaveFileTest(){
        General sut = new General("Maciek", 100);
        try{
            sut.buySoldier(new Soldier("", 0));
        }

    }
}
