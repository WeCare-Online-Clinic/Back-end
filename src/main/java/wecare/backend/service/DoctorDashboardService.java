package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.model.PatientClinicProfile;
import wecare.backend.model.dto.ConsultedPatientsCount;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.repository.ClinicDateRepository;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.repository.PatientClinicProfileRepository;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DoctorDashboardService {

    @Autowired
    private PatientClinicProfileRepository patientClinicProfileRepository;

    @Autowired
    private DoctorRepository doctorRpo;

    @Autowired
    private ClinicDateRepository clinicDateRepo;

    //Data cards in Doctor Dashboard
    public List<DoctorDataCard> getDoctorCardDetails(Integer doctorId) {
        Optional<Doctor> doctor = doctorRpo.findById(doctorId); //find the doctor by id
        Doctor resultDoctor = doctor.get(); //store the doctor instance in result doctor
        Integer clinicId = resultDoctor.getClinic().getId(); //get the doctor's clinic id


        List<DoctorDataCard> doctorDataCardList = new ArrayList<>(); //this is the object array return finally

        //Total Registered Patients In Clinic Card
        DoctorDataCard patientCountObject = new DoctorDataCard(); //make an instance to store the patient count in the clinic
        Integer patientCountInClinic = patientClinicProfileRepository.getPatientCountInClinic(clinicId);
        patientCountObject.setName("Total Registered Patients In Clinic");
        patientCountObject.setValue(Integer.toString(patientCountInClinic));
        doctorDataCardList.add(patientCountObject); //push the instance to the object array

        //Patients Registered In Last Week
        DoctorDataCard patientCountLastweekObject = new DoctorDataCard();
        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //convert the current date to a simple format
        LocalDate weekBeforeToday = LocalDate.now().minusDays(7); //get the date week before today
        Integer patientCountLastweek = patientClinicProfileRepository.getPatientCountInLastweek(clinicId, toDay, weekBeforeToday); //get the last week registered  patient count to the clinic
        patientCountLastweekObject.setName("Patients Registered In Last Week");
        patientCountLastweekObject.setValue(Integer.toString(patientCountLastweek));
        doctorDataCardList.add(patientCountLastweekObject);


        //Next clinic Date Card
        List<DoctorSchedule> doctorSchedule = resultDoctor.getDoctorSchedules(); //get doctor schedule details of the doctor
        ArrayList scheduleDays = new ArrayList(); //make an array to store the schedule days
        for (int k = 0; k < doctorSchedule.size(); k++) {
            scheduleDays.add(doctorSchedule.get(k).getClinicSchedule().getDay().toUpperCase()); //[MONDAY,WEDNESSDAY,FRIDAY]
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

                    doctorDataCardList.add(nextClinicDateObject); //add the instance to the array which will be return at the end
                    //get number of patients in next clinic
                    DoctorDataCard patientCountInNextClinicObject = new DoctorDataCard();
                    ZoneId systemTimeZone = ZoneId.systemDefault();
                    ZonedDateTime zoneDateTime = nextDate.atStartOfDay(systemTimeZone);
                    Date utilDate = Date.from(zoneDateTime.toInstant());
                    Integer noOfPatientInNextClinic = clinicDateRepo.getPatientCountOfNextClnic(utilDate, clinicId);
                    patientCountInNextClinicObject.setName("Patients in Next Clinic");
                    patientCountInNextClinicObject.setValue(Integer.toString(noOfPatientInNextClinic));
                    doctorDataCardList.add(patientCountInNextClinicObject);


                    break outerloop;

                }
            }
        }


        return doctorDataCardList;
    }

    //Diognosis chart data
    public ArrayList<Integer> getDiognosisData(Integer clinicId) {

        ArrayList<Integer> diognosisData = new ArrayList<>();

        if (clinicId == 1) {
            Integer coronaryCount = patientClinicProfileRepository.getCoronoryDiseasePatientCount();
            diognosisData.add(coronaryCount);
            Integer peripheralCount = patientClinicProfileRepository.getPeripheralDiseasePatientCount();
            diognosisData.add(peripheralCount);
            Integer rheumaticCount = patientClinicProfileRepository.getRheumaticDiseasePatientCount();
            diognosisData.add(rheumaticCount);
        }
        if (clinicId == 2) {
            Integer pepticUlcerCount = patientClinicProfileRepository.getPepticUlcerDiseasePatientCount();
            diognosisData.add(pepticUlcerCount);
            Integer CrohnsCount = patientClinicProfileRepository.getCrohnsDiseasePatientCount();
            diognosisData.add(CrohnsCount);
        }


        return diognosisData;
    }


    //Patient Age chart data
    public ArrayList<Integer> getPatientsAgeCount(Integer clinicId) {

        ArrayList<Integer> patientAgeData = new ArrayList<>();  //this is the array which return finally

        List<PatientClinicProfile> patientListInClinic = patientClinicProfileRepository.getPatientsInClinic(clinicId);
        ArrayList<LocalDate> patientDOB = new ArrayList<>(); //array to store patients' birthday in simple fromat
        ArrayList<Integer> patientAge = new ArrayList<>();
        LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //generate today date
        for (int i = 0; i < patientListInClinic.size(); i++) {
            patientDOB.add(patientListInClinic.get(i).getPatient().getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());// convert birthday to simple format and push into patientDOB arry
        }

        for (int j = 0; j < patientDOB.size(); j++) {
            Integer age = Period.between(patientDOB.get(j), today).getYears();
            patientAge.add(age);

        }
        int under10 = 0;
        int under20 = 0;
        int under30 = 0;
        int under40 = 0;
        int under50 = 0;
        int under60 = 0;
        int under70 = 0;
        int under80 = 0;
        int greater80 = 0;
        Integer sizeOfpatientAge = patientAge.size();
        for (int k = 0; k < sizeOfpatientAge; k++) {
            int currentAge = patientAge.get(k);
            if (currentAge <= 10) {
                under10++;
            } else if (currentAge <= 20) {
                under20++;
            } else if (currentAge <= 30) {
                under30++;
            } else if (currentAge <= 40) {
                under40++;
            } else if (currentAge <= 50) {
                under50++;
            } else if (currentAge <= 60) {
                under60++;
            } else if (currentAge <= 70) {
                under70++;
            } else if (currentAge <= 80) {
                under80++;
            } else {
                greater80++;
            }

        }
        patientAgeData.add(under10);
        patientAgeData.add(under20);
        patientAgeData.add(under30);
        patientAgeData.add(under40);
        patientAgeData.add(under50);
        patientAgeData.add(under60);
        patientAgeData.add(under70);
        patientAgeData.add(under80);
        patientAgeData.add(greater80);


        return patientAgeData;
    }

    //Patient clinic chart
    public ArrayList<Integer> getPatientsInClinic(Integer clinicId) {

        ArrayList<Integer> patientsInClinic = new ArrayList<>();
        ArrayList<LocalDate> patientRegDates = (ArrayList<LocalDate>) patientClinicProfileRepository.getPatientsRegDatesInClinic(clinicId);
        ArrayList<Integer> months = new ArrayList<>();//for store the months
        Integer patientRegDatesSize = patientRegDates.size();
        for (int i = 0; i < patientRegDatesSize; i++) {
            Integer month = patientRegDates.get(i).getMonthValue();
            months.add(month);
        }
        int countJan = Collections.frequency(months, 1); //count duplicate values in array
        patientsInClinic.add(countJan);
        int countFeb = Collections.frequency(months, 2);
        patientsInClinic.add(countFeb);
        int countMarch = Collections.frequency(months, 3);
        patientsInClinic.add(countMarch);
        int countApril = Collections.frequency(months, 4);
        patientsInClinic.add(countApril);
        int countMay = Collections.frequency(months, 5);
        patientsInClinic.add(countMay);
        int countJune = Collections.frequency(months, 6);
        patientsInClinic.add(countJune);
        int countJuly = Collections.frequency(months, 7);
        patientsInClinic.add(countJuly);
        int countAugust = Collections.frequency(months, 8);
        patientsInClinic.add(countAugust);
        int countSep = Collections.frequency(months, 9);
        patientsInClinic.add(countSep);
        int countOct = Collections.frequency(months, 10);
        patientsInClinic.add(countOct);
        int countNov = Collections.frequency(months, 11);
        patientsInClinic.add(countNov);
        int countDec = Collections.frequency(months, 12);
        patientsInClinic.add(countDec);


        return patientsInClinic;
    }

    //Consulted Patients data
    public ArrayList<ConsultedPatientsCount> getConsultedPatientsData(Integer doctorId) {
        ArrayList<ConsultedPatientsCount> consultedPatients = new ArrayList<>(); // this is the array which return finally

        Optional<Doctor> doctor = doctorRpo.findById(doctorId);
        Doctor resultDoctor = doctor.get();
        Integer clinicId = resultDoctor.getClinic().getId(); //get the clinic id

        List<DoctorSchedule> doctorSchedule = resultDoctor.getDoctorSchedules(); //get doctor schedule details of the doctor
        ArrayList scheduleDays = new ArrayList(); //make an array to store the schedule days
        for (int k = 0; k < doctorSchedule.size(); k++) {
            scheduleDays.add(doctorSchedule.get(k).getClinicSchedule().getDay().toUpperCase()); //[MONDAY,WEDNESSDAY,FRIDAY]
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
