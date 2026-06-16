package de.rene.threebeans.controller;

import de.rene.threebeans.service.BohneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bohnen")
public class BohneController {

    private final BohneService bohneService;

    public BohneController(BohneService bohneService) {
        this.bohneService = bohneService;
    }

    @DeleteMapping("/{id}")
    public void deleteBohne(@PathVariable Long id) {
        bohneService.deleteBohne(id);
    }
}