package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.repository.PatientClinicProfileRepository;


import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorDashboardService {

    @Autowired
    private PatientClinicProfileRepository patientClinicProfileRepository;

    @Autowired
    private DoctorRepository doctorRpo;

    public List<DoctorDataCard> getDoctorCardDetails(Integer doctorId) {
        Optional<Doctor> doctor = doctorRpo.findById(doctorId); //find the doctor by id
        Doctor resultDoctor = doctor.get(); //store the doctor instance in result doctor
        Integer clinicId = resultDoctor.getClinic().getId(); //get the doctor's clinic id


        List<DoctorDataCard> doctorDataCardList = new ArrayList<>(); //this is the object array return finally

        //Total Registered Patients In Clinic Card
        DoctorDataCard patientCountObject = new DoctorDataCard(); //make an instance to store the patient count in the clinic
        Integer patientCountInClinic = patientClinicProfileRepository.getPatientCountInClinic(clinicId);
//        System.out.println(" Full Patient Count"+patientCountInClinic);
        patientCountObject.setName("Total Registered Patients In Clinic");
        patientCountObject.setValue(Integer.toString(patientCountInClinic));
        doctorDataCardList.add(patientCountObject); //push the instance to the object array

        //Patients Registered In Last Week
        DoctorDataCard patientCountLastweekObject = new DoctorDataCard();
        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //convert the current date to a simple format
        LocalDate weekBeforeToday = LocalDate.now().minusDays(7); //get the date week before today
        Integer patientCountLastweek = patientClinicProfileRepository.getPatientCountInLastweek(clinicId, toDay, weekBeforeToday); //get the last week registered  patient count to the clinic
//        System.out.println(" Patient Count Lastweek"+patientCountLastweek);
        patientCountLastweekObject.setName("Patients Registered In Last Week");
        patientCountLastweekObject.setValue(Integer.toString(patientCountLastweek));
        doctorDataCardList.add(patientCountLastweekObject);


        //Next clinic Date Card
        List<DoctorSchedule> doctorSchedule = resultDoctor.getDoctorSchedules();
        ArrayList scheduleDays = new ArrayList();
        for (int k = 0; k < doctorSchedule.size(); k++) {
            scheduleDays.add(doctorSchedule.get(k).getClinicSchedule().getDay().toUpperCase());
        }
        DoctorDataCard nextClinicDateObject = new DoctorDataCard();
        LocalDate checkDate = toDay;
        outerloop:
        for (int j = 1; j < 7; j++) {
            LocalDate nextDate = checkDate.plusDays(j);
            DayOfWeek day = nextDate.getDayOfWeek();
            for (int i = 0; i < scheduleDays.size(); i++) {
                if (day.name().equals(scheduleDays.get(i))) {
                    nextClinicDateObject.setName("Next clinic Date");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy LLLL dd");
                    String formattedString = nextDate.format(formatter);
                    nextClinicDateObject.setValue(formattedString);

                    doctorDataCardList.add(nextClinicDateObject);
                    break outerloop;

                }
            }
        }

        //Patients in Next Clinic Date
        DoctorDataCard patientCountInNextClinicObject = new DoctorDataCard();
        patientCountInNextClinicObject.setName("Patients in Next Clinic");
        patientCountInNextClinicObject.setValue("56");
        doctorDataCardList.add(patientCountInNextClinicObject);


        return doctorDataCardList;
    }


}
