package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class KeyBinds {
    private ArrayList<Integer> myKeys;

    public KeyBinds(){
        this.myKeys = new ArrayList<Integer>(5);
        this.myKeys.add(65); // key Id for a
        this.myKeys.add(68); // key Id for d
        this.myKeys.add(83); // key Id for s
        this.myKeys.add(87); // key Id for w
        this.myKeys.add(32); // key Id for space
    }

    public KeyBinds(ArrayList<Integer> myKeys){
        this.myKeys = myKeys;
    }

    public ArrayList<Integer> setKeys(ArrayList<Integer> myKeys){
        this.myKeys = myKeys;
        return myKeys;
    }

    public ArrayList<Integer> getKeys(){
        return myKeys;
    }
    
}
