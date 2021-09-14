package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.LabTechnician;
import wecare.backend.service.LabTechService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class LabTechController {

    @Autowired
    private LabTechService labTechService;

    @GetMapping("/labTech/info/{id}")
    public LabTechnician getLabTechInfo(@PathVariable Integer id) {
        return labTechService.getLabTechInfo(id);
    }
}
