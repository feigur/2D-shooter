package com.Hopur26.D.shooter.storage.Entities;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Embeddable
public class Last5Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    ArrayList<String> last5Games;
}
