package storage.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    private String password;
    private String up;


    public long getID() { return ID; }
    public void setID(long ID) { this.ID = ID; }

}
