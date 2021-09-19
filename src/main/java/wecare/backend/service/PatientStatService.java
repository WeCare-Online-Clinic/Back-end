package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.PatientClinicProfile;
import wecare.backend.model.Report;
import wecare.backend.model.Test;
import wecare.backend.model.dto.PatientStatDataDTO;
import wecare.backend.model.dto.PatientStatTitleDTO;
import wecare.backend.repository.PatientClinicProfileRepository;
import wecare.backend.repository.PatientLabReports;
import wecare.backend.repository.ReportRepository;
import wecare.backend.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientStatService {
    @Autowired
    private PatientLabReports patientLabReportsRepo;

    @Autowired
    private PatientClinicProfileRepository patientClinicProfileRepo;

    @Autowired
    private TestRepository testRepository;

    public List<PatientStatTitleDTO> getPatientStatistics(Integer patientId) {
        List<PatientStatTitleDTO> patientStatData=new ArrayList<PatientStatTitleDTO>(); //array list finally return


        List<PatientClinicProfile> patientClinicProfile= patientClinicProfileRepo.findByPatientId(patientId);
        for(int j=0; j<patientClinicProfile.size(); j++){
            List<Test> testTypes=testRepository.getTestListByClinicId(patientClinicProfile.get(j).getClinic().getId());
            for(int z=0; z<testTypes.size(); z++){
                PatientStatTitleDTO newPatientStatTitleDTO=new PatientStatTitleDTO();
                newPatientStatTitleDTO.setTestName(testTypes.get(z).getName());
                newPatientStatTitleDTO.setField1(testTypes.get(z).getField1());
                newPatientStatTitleDTO.setField2(testTypes.get(z).getField2());

                Integer testId=testTypes.get(z).getId();
                List<Report> patientsReports=patientLabReportsRepo.getLabReportsByTestIdForStat(patientId,testId);

                List<PatientStatDataDTO> dataList=new ArrayList<PatientStatDataDTO>();
                for(int n=0; n<patientsReports.size(); n++){

                    PatientStatDataDTO  patientStatDataDTO=new PatientStatDataDTO();
                    patientStatDataDTO.setData1(patientsReports.get(n).getData1());
                    patientStatDataDTO.setData2(patientsReports.get(n).getData2());
                    patientStatDataDTO.setDate(patientsReports.get(n).getTestDate());

                    dataList.add(patientStatDataDTO);
                }
                newPatientStatTitleDTO.setDataList(dataList);
                patientStatData.add(newPatientStatTitleDTO);
            }
        }
        return  patientStatData;
    }
}
