package de.rene.threebeans.model;

import jakarta.persistence.*;

@Entity
public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int alter;

    public Nutzer() {
    }

    public Nutzer(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}