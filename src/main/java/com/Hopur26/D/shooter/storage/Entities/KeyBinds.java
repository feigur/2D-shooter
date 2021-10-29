package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class KeyBinds {
    ArrayList<Integer> myKeys;

    public KeyBinds(){
        this.myKeys = new ArrayList<Integer>(5);
        this.myKeys.set(0,0); // key Id fyrir a
        this.myKeys.set(1,1); // key Id fyrir d
        this.myKeys.set(2,2); // key Id fyrir s
        this.myKeys.set(3,3); // key Id fyrir w
        this.myKeys.set(4,4); // key Id fyrir space
    }

    public KeyBinds(ArrayList<Integer> myKeys){
        this.myKeys = myKeys;
    }

    public ArrayList<Integer> setKeyBinds(ArrayList<Integer> myKeys){
        this.myKeys = myKeys;
        return myKeys;
    }

    public ArrayList<Integer> getMyKeys(){
        return myKeys;
    }
    
}
