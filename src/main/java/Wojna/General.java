package Wojna;

import java.util.List;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class General {
    private String name;
    private int gold;
    private Army army;
    private int turn = 1;
    private Secretary Secretary = new Secretary();

    public General(String name, int gold) {
        this.name = name;
        this.gold = gold;
        
    }
    private boolean CheckIfDead(Soldier soldier){
        if (soldier.getExperience() == 0){
            return true;
        }
        return false;
    }
    public int lostBattle(){
        for (Soldier soldier : army.getArmy()){
            soldier.decreaseExperience();
            if (CheckIfDead(soldier)){
                army.removeSoldier(soldier);
            }
        }
        return (int)0.1*this.gold;
    }
    public void drawBattle(){
        army.randomRemove();
    }

    
    private void wonBattle(){
        for (Soldier soldier : army.getArmy()){
            soldier.increaseExperience();
        }

    }
    public void recieveGold(int amount){
        this.gold += amount;
    }
    public List<Soldier> conductManoeuvre(List<Soldier> soldiers) {
        int cost = 0;
        for (Soldier soldier : soldiers) {
            cost += soldier.getCost();
        }
        if (gold >= cost) {
            gold -= cost;
            for (Soldier soldier : soldiers) {
                soldier.increaseExperience();
                
            }
            this.turn += 1;
        }
        return soldiers;
    }

    public void attack(General enemy) {
        if (this.army.getArmy().size() > 0){
            if (this.army.getTotalStrength() > enemy.army.getTotalStrength()){
                wonBattle();
                this.gold += enemy.lostBattle();
            }
            else if (this.army.getTotalStrength() == enemy.army.getTotalStrength()){
                drawBattle();
                enemy.drawBattle();
            }
            else if (this.army.getTotalStrength() < enemy.army.getTotalStrength()){
                int lost_gold = lostBattle();
                enemy.recieveGold(lost_gold);
                gold -= lost_gold;
    
            }
            this.turn += 1;
        }
    }
    public void buySoldier(Soldier soldier,int amount){
        int sumOfCosts = soldier.getCost()*amount;
        if (this.gold >= sumOfCosts){
            this.gold -= sumOfCosts;
            for (int i = 0; i<amount; i++){
                army.addSoldier(soldier);
            }
            
        }
    }
    public String writeArmy(){
        String result = "";
        for (Soldier soldier : army.getArmy()){
            result += soldier.getRank()+soldier.getExperience()+'\n';
        }
        return result;

    }
    
    public void CreateSave(String name){
        
        
        try{
            File saveFile = new File(name+".txt");
            saveFile.createNewFile();
        }
        catch (IOException e){
            
        }
        try{
            FileWriter saveWriter = new FileWriter(name+".txt");
            saveWriter.write(name+'\n'+gold+'\n'+turn+'\n'+writeArmy());
            saveWriter.close();
        }
        catch (IOException e){

        }
        
        


    }

    // Getters and Setters
}
