package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Embeddable
public class Last5Games {

    private ArrayList<String> last5Games;

    public Last5Games(){
        this.last5Games = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            last5Games.add("x-x");
        }
    }

    public Last5Games(ArrayList<String> last5Games){
        this.last5Games = last5Games;
    }

    public ArrayList<String> addGame(String score) {
        for (int i = 3; i > -1; i--) {
            this.last5Games.set(i+1,last5Games.get(i));
        }
        this.last5Games.set(0,score);
        return last5Games;
    }

    public ArrayList<String> getLast5Games(){
        return last5Games;
    }
}
