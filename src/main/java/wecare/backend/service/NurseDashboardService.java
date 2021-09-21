package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wecare.backend.model.*;
import wecare.backend.model.dto.ConsultedPatientsCount;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.repository.ClinicDateRepository;
import wecare.backend.repository.NurseRepository;
import wecare.backend.repository.PatientClinicProfileRepository;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NurseDashboardService {

    @Autowired
    private PatientClinicProfileRepository patientClinicProfileRepository;

    @Autowired
    private NurseRepository nurserRpo;

    @Autowired
    private ClinicDateRepository clinicDateRepo;

    //Data cards in Doctor Dashboard
    public List<DoctorDataCard> getNurseCardDetails(Integer nurseId) {
        Optional<Nurse> nurse = nurserRpo.findById(nurseId); //find the nurse by id
        Nurse resultNurse = nurse.get(); //store the nurse instance in result nurse
        Integer clinicId = resultNurse.getClinic().getId(); //get the nurse's clinic id
        System.out.println(resultNurse);


        List<DoctorDataCard> nurseDataCardList = new ArrayList<>(); //this is the object array return finally

        //Total Registered Patients In Clinic Card
        DoctorDataCard patientCountObject = new DoctorDataCard(); //make an instance to store the patient count in the clinic
        Integer patientCountInClinic = patientClinicProfileRepository.getPatientCountInClinic(clinicId);
        patientCountObject.setName("Total Registered Patients In Clinic");
        patientCountObject.setValue(Integer.toString(patientCountInClinic));
        nurseDataCardList.add(patientCountObject); //push the instance to the object array

        //Patients Registered In Last Week
        DoctorDataCard patientCountLastweekObject = new DoctorDataCard();
        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //convert the current date to a simple format
        LocalDate weekBeforeToday = LocalDate.now().minusDays(7); //get the date week before today
        Integer patientCountLastweek = patientClinicProfileRepository.getPatientCountInLastweek(clinicId, toDay, weekBeforeToday); //get the last week registered  patient count to the clinic
        patientCountLastweekObject.setName("Patients Registered In Last Week");
        patientCountLastweekObject.setValue(Integer.toString(patientCountLastweek));
        nurseDataCardList.add(patientCountLastweekObject);


        //Next clinic Date Card
        List<NurseSchedule> nurseSchedule = resultNurse.getNurseSchedule(); //get doctor schedule details of the doctor
        ArrayList scheduleDays = new ArrayList(); //make an array to store the schedule days
        for (int k = 0; k < nurseSchedule.size(); k++) {
            scheduleDays.add(nurseSchedule.get(k).getClinicSchedule().getDay().toUpperCase()); //[MONDAY,WEDNESSDAY,FRIDAY]
        }
        DoctorDataCard nextClinicDateObject = new DoctorDataCard(); //make an instance of datacard
        LocalDate checkDate = toDay; //generate today date
        outerloop:
        //this is use for break nested for loop
        for (int j = 1; j < 7; j++) {
            LocalDate nextDate = checkDate.plusDays(j); //get the tomorrow date
            DayOfWeek day = nextDate.getDayOfWeek(); //get the day from tomorrow date - ( 2021-9-1)=>WEDNESSDAY
            for (int i = 0; i < scheduleDays.size(); i++) {
                if (day.name().equals(scheduleDays.get(i))) { //check if the next day is included in the schedule array
                    nextClinicDateObject.setName("Next clinic Date");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
                    String formattedString = nextDate.format(formatter);
                    nextClinicDateObject.setValue(formattedString); //convert date into string (because doctor data card values are not in same data type)

                    nurseDataCardList.add(nextClinicDateObject); //add the instance to the array which will be return at the end
                    //get number of patients in next clinic
                    DoctorDataCard patientCountInNextClinicObject = new DoctorDataCard();
                    ZoneId systemTimeZone = ZoneId.systemDefault();
                    ZonedDateTime zoneDateTime = nextDate.atStartOfDay(systemTimeZone);
                    Date utilDate = Date.from(zoneDateTime.toInstant());
                    Integer noOfPatientInNextClinic = clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(clinicId, utilDate).getNoPatients();
                    patientCountInNextClinicObject.setName("Patients in Next Clinic");
                    patientCountInNextClinicObject.setValue(Integer.toString(noOfPatientInNextClinic));
                    nurseDataCardList.add(patientCountInNextClinicObject);


                    break outerloop;

                }
            }
        }


        return nurseDataCardList;
    }



    //Consulted Patients data
    public ArrayList<ConsultedPatientsCount> getConsultedPatientsData(Integer nurseId) {
        ArrayList<ConsultedPatientsCount> consultedPatients = new ArrayList<>(); // this is the array which return finally

        Optional<Nurse> nurse = nurserRpo.findById(nurseId);
        Nurse resultNurse = nurse.get();
        Integer clinicId = resultNurse.getClinic().getId(); //get the clinic id

        List<NurseSchedule> nurseSchedule = resultNurse.getNurseSchedule(); //get nurse schedule details of the nurse
        ArrayList scheduleDays = new ArrayList(); //make an array to store the schedule days
        for (int k = 0; k < nurseSchedule.size(); k++) {
            scheduleDays.add(nurseSchedule.get(k).getClinicSchedule().getDay().toUpperCase()); //[MONDAY,WEDNESSDAY,FRIDAY]
        }

        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //convert the current date to a simple format
        LocalDate MonthBeforeToday = LocalDate.now().minusDays(30); //get the date month before today

        for (int i = 0; i < 30; i++) {
            LocalDate nextDate = MonthBeforeToday.plusDays(i);
            DayOfWeek dayOfWeekNextDay = nextDate.getDayOfWeek(); //get the next from day which past 30 days- ( 2021-9-1)=>WEDNESSDAY
            for (int j = 0; j < scheduleDays.size(); j++) {
                if (dayOfWeekNextDay.name().equals(scheduleDays.get(j))) { //check if the next day is included in the schedule array
                    //convert LocalDate to Date
                    ZoneId systemTimeZone = ZoneId.systemDefault();
                    ZonedDateTime zoneDateTime = nextDate.atStartOfDay(systemTimeZone);
                    Date utilDate = Date.from(zoneDateTime.toInstant()); //utilDate means Date type of nextClinicDate

                    //get the consulted patients of the perticular date
                    Integer consultedCount=clinicDateRepo.getConsultedPatients(clinicId,utilDate);
                    ConsultedPatientsCount consultedPatientsCountObject= new ConsultedPatientsCount();
                    consultedPatientsCountObject.setClinicDate(nextDate);
                    consultedPatientsCountObject.setCount(consultedCount);

                    consultedPatients.add(consultedPatientsCountObject);

                }
            }

        }


        return consultedPatients;
    }


}

