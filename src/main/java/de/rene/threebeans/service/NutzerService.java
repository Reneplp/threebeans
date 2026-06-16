package de.rene.threebeans.service;

import de.rene.threebeans.model.Nutzer;
import de.rene.threebeans.repository.NutzerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutzerService {

    private final NutzerRepository nutzerRepository;

    public NutzerService(NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }

    public List<Nutzer> getAlleNutzer() {
        return nutzerRepository.findAll();
    }

    public Nutzer saveNutzer(Nutzer nutzer) {
        return nutzerRepository.save(nutzer);
    }
}