package de.rene.threebeans.controller;

import de.rene.threebeans.model.Tageseintrag;
import de.rene.threebeans.service.TageseintragService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tageseintraege")
public class TageseintragController {

    private final TageseintragService tageseintragService;

    public TageseintragController(TageseintragService tageseintragService) {
        this.tageseintragService = tageseintragService;
    }

    @GetMapping
    public List<Tageseintrag> getAlleTageseintraege() {
        return tageseintragService.getAlleTageseintraege();
    }

    @PostMapping
    public Tageseintrag addTageseintrag(@RequestBody Tageseintrag tageseintrag) {
        return tageseintragService.saveTageseintrag(tageseintrag);
    }

    @DeleteMapping("/{id}")
    public void deleteTageseintrag(@PathVariable Long id) {
        tageseintragService.deleteTageseintrag(id);
    }
}