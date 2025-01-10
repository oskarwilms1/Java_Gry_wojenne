package Wojna;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Army {
    private List<Soldier> soldiers;
    

    public Army() {
        this.soldiers = new ArrayList<>();
    }

    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
    }
    public void removeSoldier(Soldier soldier){
        soldiers.remove(soldier);
    }
    public void randomRemove(){
        Random rand = new Random();
        removeSoldier(soldiers.get(rand.nextInt(soldiers.size())));
    }

    public int getTotalStrength() {
        return soldiers.stream().mapToInt(Soldier::calculateStrength).sum();
    }


    // Getters and Setters
    public List<Soldier> getArmy(){
        return this.soldiers;
    }
}

