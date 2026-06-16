package de.rene.threebeans.repository;

import de.rene.threebeans.model.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutzerRepository extends JpaRepository<Nutzer, Long> {
}