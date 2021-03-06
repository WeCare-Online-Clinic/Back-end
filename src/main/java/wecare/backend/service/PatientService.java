package wecare.backend.service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.*;
import wecare.backend.model.dto.ChangeClinicDate;
import wecare.backend.model.dto.NextClinic;
import wecare.backend.model.dto.PatientMessageList;
import wecare.backend.repository.*;

@Service
public class PatientService {
	
	private static final String resultedPatient = null;

	@Autowired
	private  PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ClinicAppointmentRepository clinicAppointmentRepo;

	@Autowired
	private PatientClinicDataRepository patientClinicDataRepo;

	@Autowired
	private ClinicScheduleRepository clinicScheduleRepository;

	@Autowired
	private PatientRequestRepository patientRequestRepo;

	@Autowired
	private PatientClinicProfileRepository patientClinicProfileRepo;

	@Autowired
	private PatientMessageRepository patientMessageRepo;

	@Autowired
	private ClinicMessageRepository clinicMessageRepo;

	@Autowired
	private ClinicDateMessageRepository clinicDateMessageRepo;

	@Autowired
	private PatientLabReports patientLabReportsRepo;

	@Autowired
	private ClinicRepository clinicRepo;
	
	public Patient addPatient(Patient patient) throws UserCollectionException{
    Patient resultedPatient = patientRepo.findByEmail(patient.getEmail());
		Patient newPatient = null;
		User newUser = new User();
		if(resultedPatient==null) {
			newPatient =  patientRepo.saveAndFlush(patient);
			newUser.setId(newPatient.getId());
			newUser.setUserRole("patient");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
            newUser.setEmail(newPatient.getEmail());
			userRepo.save(newUser);
			return newPatient;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}
	
	public List<Patient> getAllPatient(){
		List<Patient> patient = patientRepo.findAll();
		return patient;
	}
	
	public List<Patient> getPatientProfileById(Integer id) {
		List<Patient> patient = patientRepo.getPatientProfileById(id);
		return patient;
	
	}
	
	public List<Patient> getPatientProfileByName(String name){
		List<Patient> patient=patientRepo.findByFirstNameLike(name);
		return patient;
	}

	public Patient getPatientInfo(Integer id){
		return patientRepo.findById(id).get();
	}

	public List<NextClinic> getNextClinicDetails(Integer patientId){
		List<ClinicAppointment> clinicAppointments= clinicAppointmentRepo.getNextClinicDetails(patientId);
		List<NextClinic> nextClinics = new ArrayList<>(clinicAppointments.size());
		for (ClinicAppointment clinicAppointment : clinicAppointments) {
			NextClinic nextClinic = new NextClinic();
			nextClinic.setClinicAppointment(clinicAppointment);
			nextClinic.setClinic(clinicRepo.findByClinicSchedulesId(clinicAppointment.getClinicDate().getClinicSchedule().getId()));
			nextClinics.add(nextClinic);
		}
		return nextClinics;
	}

	public List<PatientClinicData> getPatientClinicDataList(Integer id){
		Date date = new Date();
		return patientClinicDataRepo.findAllByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(id, date);
	}

	public ArrayList<LocalDate> getRequestDates(ChangeClinicDate changeClinicDate) {
		ArrayList<LocalDate> requestedDates = new ArrayList<>();

		LocalDate currentClinicDate = changeClinicDate.getCurrentClinicDate();
		List<String> clinicDays = clinicScheduleRepository.getClinicShceduleByClinicId(changeClinicDate.getClinicId());

		for (int j = 1; j <= 7; j++) {
			LocalDate nextDate = currentClinicDate.plusDays(j);
			DayOfWeek dayOfWeekNextDay = nextDate.getDayOfWeek(); //get the next from day which past 30 days- ( 2021-9-1)=>WEDNESSDAY
			for (String clinicDay : clinicDays) {
				if (dayOfWeekNextDay.name().equals(clinicDay.toUpperCase())) { //check if the next day is included in the schedule array
					requestedDates.add(nextDate);
				}
			}
		}


		return requestedDates;
	}

    public Integer savePatientRequest(PatientRequest patientRequest) {

		PatientRequest resultPatientRequest=patientRequestRepo.findIsRequestIsExisting(patientRequest.getPatient(),patientRequest.getClinicDate(),patientRequest.getClinic());
		if(resultPatientRequest==null){
			patientRequest.setChanged(false);
			patientRequestRepo.saveAndFlush(patientRequest);
			return 1;
		}
		else{
			return 0;
		}
    }

    public PatientMessageList getMessages(Integer id) {
		Date date = new Date();

		PatientMessageList patientMessageList = new PatientMessageList();
		patientMessageList.setPatientMessages(patientMessageRepo.findByPatientId(id));

		List<PatientClinicProfile> patientClinicProfiles = patientClinicProfileRepo.findByPatientId(id);
		List<ClinicMessage> clinicMessages = new ArrayList<>();
		for (PatientClinicProfile patientClinicProfile : patientClinicProfiles) {
			List<ClinicMessage> clinicMessages1 = clinicMessageRepo.findAllByClinicIdOrderByDateAsc(patientClinicProfile.getClinic().getId());
				List<ClinicMessage> clinicMessages2 = new ArrayList<>(clinicMessages.size() + clinicMessages1.size());
				clinicMessages2.addAll(clinicMessages);
				clinicMessages2.addAll(clinicMessages1);
				clinicMessages = clinicMessages2;
		}
		patientMessageList.setClinicMessages(clinicMessages);

		List<ClinicAppointment> clinicAppointments = clinicAppointmentRepo.findByPatientIdAndClinicDateDateGreaterThan(id, date);
		List<ClinicDateMessage> clinicDateMessages = new ArrayList<>();
		for (ClinicAppointment clinicAppointment : clinicAppointments) {
			List<ClinicDateMessage> clinicDateMessages1 = clinicDateMessageRepo.findAllByClinicDateIdOrderByDateAsc(clinicAppointment.getClinicDate().getId());
				List<ClinicDateMessage> clinicDateMessages2 = new ArrayList<>(clinicDateMessages.size() + clinicDateMessages1.size());
				clinicDateMessages2.addAll(clinicDateMessages);
				clinicDateMessages2.addAll(clinicDateMessages1);
				clinicDateMessages = clinicDateMessages2;
		}
		patientMessageList.setClinicDateMessages(clinicDateMessages);

		return patientMessageList;
    }

	public List<Report> getLabReportDetails(Integer patientId){
		List<Report> labReports=patientLabReportsRepo.getLabReportDetails(patientId);
		return labReports;
	}

}
