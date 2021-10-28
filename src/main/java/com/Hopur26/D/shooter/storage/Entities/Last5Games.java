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
        last5Games = new ArrayList<String>(5);
    }

    public Last5Games(ArrayList<String> last5Games){
        last5Games = last5Games;
    }
}
