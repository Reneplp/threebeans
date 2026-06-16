package de.rene.threebeans.service;

import de.rene.threebeans.model.Tageseintrag;
import de.rene.threebeans.repository.TageseintragRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TageseintragService {

    private final TageseintragRepository tageseintragRepository;

    public TageseintragService(TageseintragRepository tageseintragRepository) {
        this.tageseintragRepository = tageseintragRepository;
    }

    public List<Tageseintrag> getAlleTageseintraege() {
        return tageseintragRepository.findAll();
    }

    public Tageseintrag saveTageseintrag(Tageseintrag tageseintrag) {
        return tageseintragRepository.save(tageseintrag);
    }

    public void deleteTageseintrag(Long id) {
        tageseintragRepository.deleteById(id);
    }
}