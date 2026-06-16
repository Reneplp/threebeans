package de.rene.threebeans.model;

import jakarta.persistence.*;

@Entity
public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer alter;

    public Nutzer() {
    }

    public Nutzer(String name, Integer alter) {
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

    public Integer getAlter() {
        return alter;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }
}