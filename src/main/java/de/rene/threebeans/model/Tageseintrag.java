package de.rene.threebeans.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tageseintrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;

    @Enumerated(EnumType.STRING)    // Sagt Hibernate, dass nicht der Index aus dem Enum (0,1,2,3) sondern der Wert als Text
                                    // gespeichert werden soll
    private Stimmung stimmung;

    @ManyToOne // ER-Tabelle Beziehung N:1
    @JoinColumn(name = "nutzer_id")
    private Nutzer nutzer;

    @OneToMany(mappedBy = "tageseintrag", cascade = CascadeType.ALL, orphanRemoval = true) // ER-Tabelle 1:N
    private List<Bohne> bohnen = new ArrayList<>();

    public Tageseintrag() {
    }

    public Tageseintrag(LocalDate datum, Stimmung stimmung, Nutzer nutzer) {
        this.datum = datum;
        this.stimmung = stimmung;
        this.nutzer = nutzer;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Stimmung getStimmung() {
        return stimmung;
    }

    public void setStimmung(Stimmung stimmung) {
        this.stimmung = stimmung;
    }

    public Nutzer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
    }

    public List<Bohne> getBohnen() {
        return bohnen;
    }

    public void setBohnen(List<Bohne> bohnen) {
        this.bohnen = bohnen;
    }
}