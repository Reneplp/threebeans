package de.rene.threebeans.controller;

import de.rene.threebeans.model.Nutzer;
import de.rene.threebeans.service.NutzerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutzer")
public class NutzerController {

    private final NutzerService nutzerService;

    public NutzerController(NutzerService nutzerService) {
        this.nutzerService = nutzerService;
    }

    @GetMapping
    public List<Nutzer> getAlleNutzer() {
        return nutzerService.getAlleNutzer();
    }

    @PostMapping
    public Nutzer addNutzer(@RequestBody Nutzer nutzer) {
        return nutzerService.saveNutzer(nutzer);
    }
}