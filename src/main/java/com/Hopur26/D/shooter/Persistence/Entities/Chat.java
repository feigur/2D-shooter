package com.Hopur26.D.shooter.Persistence.Entities;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private int lengthOfChat = 10;

    private  String username;

    private ArrayList<String> chat;

    public Chat(){
        this.chat= new ArrayList<String>(5);
        for (int i = 0; i < lengthOfChat; i++) {
            chat.add("N/A");
        }
    }

    public  Chat(ArrayList<String> chat){this.chat = chat;}

    public  ArrayList<String> addChat(String msg){
        for(int i = lengthOfChat-1; i>0; i--){
            String temp = chat.get(i-1);
            this.chat.set(i,temp);
        }
        this.chat.set(0,msg);
        return chat;
    }

    public  ArrayList<String> getChat() { return chat;}

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
}
