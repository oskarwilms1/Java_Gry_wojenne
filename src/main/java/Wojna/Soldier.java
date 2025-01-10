package Wojna;

public class Soldier {
    private String rank;  // 'Private', 'Corporal', 'Captain', 'Major'
    private int experience;
    private int strength;

    public Soldier(String rank,int experience) {
        this.rank = rank;
        this.experience = experience;
        this.strength = calculateStrength();
    }

    public void promote() {
        if (this.experience == rankThreshold(rank) && rank != "Major"){
            if (rank == "Private"){
                this.rank = "Corporal";
            }
            else if (rank == "Corporal"){
                this.rank = "Captain";
            }
            else if (rank == "Captain"){
                this.rank = "Major";
            }
            this.experience = 1;
        }
    }

    public void increaseExperience() {
        this.experience++;
        if (this.experience >= rankThreshold(rank)) {
            promote();
        }
    }
    public void decreaseExperience() {
        this.experience--;
        if (experience==0){
            this.rank = "Dead";
            this.strength = 0;
            
        }
    }

    public int calculateStrength() {
        int rankMultiplier = 1;
        if (rank == "Private"){
            rankMultiplier = 1;
        }
        else if (rank == "Corporal"){
            rankMultiplier = 2;
        }
        else if (rank == "Captain"){
            rankMultiplier = 3;
        }
        else if (rank == "Major"){
            rankMultiplier = 4;
        }
        return rankMultiplier * experience;
    }

    private int rankThreshold(String rank) {
        return 5*calculateStrength()/this.experience;
    }
    public int getCost(){
        int cost = 1;
        if (rank == "Private"){
            cost = 1;
        }
        else if (rank == "Corporal"){
            cost  = 2;
        }
        else if (rank == "Captain"){
            cost  = 3;
        }
        else if (rank == "Major"){
            cost  = 4;
        }
        return cost*10;
    }
    // Getters and Setters
    public String getRank(){
        return this.rank;
    }
    public int getExperience(){
        return this.experience;
    }
    public int getStrength(){
        return this.strength;
    }
}

