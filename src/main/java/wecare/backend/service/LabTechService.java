package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.LabTechnician;
import wecare.backend.repository.LabTechnicianRepository;

@Service
public class LabTechService {
    @Autowired
    private LabTechnicianRepository labTechnicianRepo;

    public LabTechnician getLabTechInfo(Integer id){
        return labTechnicianRepo.findById(id).get();
    }
}

