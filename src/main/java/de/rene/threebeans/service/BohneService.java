package de.rene.threebeans.service;

import de.rene.threebeans.model.Bohne;
import de.rene.threebeans.repository.BohneRepository;
import org.springframework.stereotype.Service;

@Service // Man muss SpringBoot sagen, dass die Klasse zur Logik gehört. Ohne Annotation = SpringBoot ignoriert die Klasse
public class BohneService {

    private final BohneRepository bohneRepository;

    public BohneService(BohneRepository bohneRepository) {
        this.bohneRepository = bohneRepository;
    }

    public void deleteBohne(Long id) {
        bohneRepository.deleteById(id);
    }
}