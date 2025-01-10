package Wojna;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;


public class GeneralTest {
    @Test 
    public void BattleDrawTest(){
        General sut1 = new General("Midas", 100000000);
        General sut2 = new General("Midas", 100000000);
        sut1.buySoldier("Private", 200);
        sut2.buySoldier("Private", 200);
        sut1.attack(sut2);
        int result = sut2.getArmy().getArmy().size();
        
        

        assertEquals(199, result);

        
    }
    @Test 
    public void BattleDeadTest(){
        General sut1 = new General("Midas", 100000000);
        General sut2 = new General("Biedak",200);
        sut1.buySoldier("Private", 200);
        sut2.buySoldier("Private", 1);
        sut1.attack(sut2);
        String result = sut2.getArmy().getArmy().get(0).getRank();
        

        assertEquals("Dead", result);

        
    }
    @Test 
    public void BattleGoldTest(){
        General sut1 = new General("Midas", 100000000);
        General sut2 = new General("Biedak",200);
        sut1.buySoldier("Private", 200);
        sut2.buySoldier("Private", 1);
        sut1.attack(sut2);
        // expected gold for sut1: 100000000 - 2000 + 19 =99998019
        int result = sut1.getGold();
        assertEquals(99998019, result);

        
    }
    @Test
    public void LostBattleTest(){
        General sut1 = new General("Loser", 100);
        sut1.buySoldier("Private", 5);
        sut1.lostBattle();
        int result = sut1.getGold();
        assertEquals(45, result);
    }
    @Test
    public void SaveFileTestGold(){
        General sut = new General("Maciek", 100);
        sut.buySoldier("Private",3);
        sut.CreateSave("FirstSave");
        sut.buySoldier("Private",5);
        sut.ReadSave("FirstSave.txt");
        int gold_result = sut.getGold();
        assertEquals(70, gold_result);
    }
    @Test
    public void SaveFileTestArmy(){
        General sut = new General("Maciek", 100);
        sut.buySoldier("Private",1);
        sut.buySoldier("Private",1);
        sut.buySoldier("Captain", 1);
        sut.CreateSave("SecondSave");
        sut.buySoldier("Private",5);
        sut.ReadSave("SecondSave.txt");
        Army army_result = sut.getArmy();
        Army expected = new Army();
        
        expected.addSoldier(new Soldier("Private", 1));
        expected.addSoldier(new Soldier("Private", 1));
        expected.addSoldier(new Soldier("Captain", 1));


        int size  = expected.getArmy().size();
        boolean result;
        for (int i = 0;i<size;i++){
            
            String expected_rank = expected.getArmy().get(i).getRank();
            String actual_rank = army_result.getArmy().get(i).getRank();
            int expected_experience = expected.getArmy().get(i).getExperience();
            int actual_experience = army_result.getArmy().get(i).getExperience();
            result = (expected_rank.equals(actual_rank) && expected_experience == actual_experience);
            assertEquals(true, result);
            
        }
    }
        @Test
        public void SaveFileTestTurn(){
            General sut = new General("Maciek", 100);
            sut.buySoldier("Private",5);
            sut.CreateSave("ThirdSave");
            sut.buySoldier("Private",1);
            sut.buySoldier("Private",1);
            sut.buySoldier("Private",1);
            sut.ReadSave("ThirdSave.txt");
            int turn_result = sut.getTurn();
            assertEquals(2, turn_result);
    }
        
        
    
    
}
