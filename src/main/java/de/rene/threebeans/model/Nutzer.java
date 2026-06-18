package de.rene.threebeans.model;

import jakarta.persistence.*;

@Entity // --> sagt Hibernate, dass diese Klasse eine Tabelle in der DB bekommt
public class Nutzer {

    @Id // --> markiert welches Feld der Primärschlüssel ist
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> auto-increment für die ID Vergabe in der DB
    private Long id;

    private String name;
    private Integer alter; // Musste Integer statt int sein, da sonst nichts in der DB gespeichert wurde

    // Leerer Konstruktor notwendig für Hibernate: Wenn eine Zeile aus der DB gelesen wird baut es erst ein leeres Objekt
    // und füllt dann die Felder über den Setter. Wenn leerer Konstruktor fehlt --> Fehlermeldung
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