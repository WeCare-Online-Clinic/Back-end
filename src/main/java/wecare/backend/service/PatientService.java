package wecare.backend.service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.ClinicAppointment;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.model.Patient;
import wecare.backend.model.PatientClinicData;
import wecare.backend.model.User;
import wecare.backend.repository.ClinicAppointmentRepository;
import wecare.backend.repository.PatientClinicDataRepository;
import wecare.backend.repository.ClinicScheduleRepository;
import wecare.backend.repository.PatientRepository;
import wecare.backend.repository.UserRepository;

@Service
public class PatientService {
	
	private static final String resultedPatient = null;

	@Autowired
	private  PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ClinicAppointmentRepository clinicAppointmentRepository;

	@Autowired
	private PatientClinicDataRepository patientClinicDataRepo;
	private ClinicScheduleRepository clinicScheduleRepository;
	
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

	public List<Patient> getPatientProfileByClinic(Integer clinicId){
		List<Patient> patient=patientRepo.findByClinicId(clinicId);
		return patient;
	}
	public Patient getPatientInfo(Integer id){
		return patientRepo.findById(id).get();
	}

	public ClinicAppointment getNextClinicDetails(Integer patientId){
		ClinicAppointment clinicAppointment=clinicAppointmentRepository.getNextClinicDetails(patientId);
		return clinicAppointment;
	}

	public List<PatientClinicData> getPatientClinicDataList(Integer id){
		Date date = new Date();
		return patientClinicDataRepo.findAllByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(id, date);
	}	
	public List<LocalDate> getRequestDates(Integer clinicId, Date currentClinicDate){
		List<LocalDate> requestedDates = null;

		List<String> clinicDays=clinicScheduleRepository.getClinicShceduleByClinicId(clinicId);
		for(int i=0; i<clinicDays.size(); i++){
			System.out.println("Day   :"+clinicDays.get(i));
		}
		return  requestedDates;
	}
}
