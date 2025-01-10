package Wojna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class General {
    private String name;
    private int gold;
    private Army army = new Army();
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
        for (Soldier soldier : this.army.getArmy()){
            soldier.decreaseExperience();
        }
        int lost_gold = (int)(0.1*this.gold);
        this.gold -= lost_gold;
        return lost_gold;
    }
    public void drawBattle(){
        army.randomRemove();
    }

    
    private void wonBattle(){
        int size = army.getArmy().size();
        for (int i=0; i<size; i++){
            army.getArmy().get(i).increaseExperience();
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
                recieveGold(enemy.lostBattle()); 
            }
            else if (this.army.getTotalStrength() == enemy.army.getTotalStrength()){
                drawBattle();
                enemy.drawBattle();
            }
            else if (this.army.getTotalStrength() < enemy.army.getTotalStrength()){
                int lost_gold = lostBattle();
                enemy.recieveGold(lost_gold);
                
    
            }
            this.turn += 1;
        }
    }
    public void buySoldier(String rank,int amount){
        Soldier soldier = new Soldier(rank,1);
        int sumOfCosts = soldier.getCost()*amount;
        if (this.gold >= sumOfCosts){
            this.gold -= sumOfCosts;
            for (int i = 0; i<amount; i++){
                army.addSoldier(soldier);
            }
            this.turn += 1;
        }
        
    }
    public String writeArmy(){
        String result = "";
        for (Soldier soldier : army.getArmy()){
            result += soldier.getRank()+' '+soldier.getExperience()+'\n';
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
            saveWriter.write(this.name+'\n'+gold+'\n'+turn+'\n'+writeArmy());
            saveWriter.close();
        }
        catch (IOException e){

        }
    }
    public void ReadSave(String name){
        ArrayList<String> savedData = new ArrayList<>();
        try{
            File ReadFile = new File(name);
            Scanner myReader = new Scanner(ReadFile);
            this.name = myReader.nextLine();
            this.gold = Integer.parseInt(myReader.nextLine());
            this.turn = Integer.parseInt(myReader.nextLine());
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              savedData.add(data);
            }
            myReader.close();
        }
        catch (IOException e){
            
        }
        Army SavedArmy = new Army();
        for(String info : savedData){
            String[] strSplit = info.split(" ");
            SavedArmy.addSoldier(new Soldier(strSplit[0], Integer.parseInt(strSplit[1])));
        }
        this.army = SavedArmy;
        
    }

    // Getters and Setters
    public int getGold(){
        return this.gold;
    }
    public Army getArmy(){
        return this.army;
    }
    public int getTurn(){
        return this.turn;
    }
}
