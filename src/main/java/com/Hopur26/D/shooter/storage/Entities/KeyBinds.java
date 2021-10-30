package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class KeyBinds {
    ArrayList<Integer> myKeys;

    public KeyBinds(){
        this.myKeys = new ArrayList<Integer>(5);
        this.myKeys.set(0,65); // key Id for a
        this.myKeys.set(1,68); // key Id for d
        this.myKeys.set(2,83); // key Id for s
        this.myKeys.set(3,87); // key Id for w
        this.myKeys.set(4,32); // key Id for space
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
