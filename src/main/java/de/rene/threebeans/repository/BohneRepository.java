package de.rene.threebeans.repository;

import de.rene.threebeans.model.Bohne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BohneRepository extends JpaRepository<Bohne, Long> {
}

// Note to self: JpaRepository<Bohne, Long> sagt Spring, dass ich
// für den Entity Typ Bohne, dessen ID Typ Long ist, CRUD Operations möchte.
// "extends" gibt mir automatisch die Methoden wie save(), findAll(), findById(), deleteById() und count() --> Spring Funktion