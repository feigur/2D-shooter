package com.Hopur26.D.shooter.Persistance;

import java.util.ArrayList;

public interface MainService {
    ArrayList<String> getLast5Games(long id);
    ArrayList<Integer> getKeys(int id);
    void setButtons(ArrayList<Integer> newKeys, Long id);
}
