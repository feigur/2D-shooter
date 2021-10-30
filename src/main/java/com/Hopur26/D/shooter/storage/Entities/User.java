package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "last5Games", column = @Column(name = "last_5_games"))
    })
    private Last5Games last5Games;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "myKeys", column = @Column(name = "my_keys"))
    })
    private KeyBinds keyBinds;


    public User() {

    }

    public User(String username, String password, KeyBinds keyBinds, Last5Games last5Games) {
        this.username = username;
        this.password = password;
        this.keyBinds = keyBinds;
        this.last5Games = last5Games;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getKeys(){
        return this.keyBinds.getKeys();
    }

    public ArrayList<String> getLast5Games(){
        return this.last5Games.getLast5Games();
    }

    public void setKeys(ArrayList<Integer> keyBinds){
        this.keyBinds.setKeys(keyBinds);
    }

    public void addGame(String lastGame){
        this.last5Games.addGame(lastGame);
    }
}
