package wecare.backend.service;



import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.ClinicDateException;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.*;
import wecare.backend.repository.*;

@Service
public class NurseService {

	@Autowired
	private NurseRepository nurseRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NurseSchedulesRepository nurseSchedulesRepo;

	@Autowired
	private ClinicAppointmentRepository clinicAppointmentRepo;

	@Autowired
	private ClinicDateRepository clinicDateRepo;

	@Autowired
	private PatientClinicProfileRepository patientClinicProfileRepo;

	public Nurse addNurse(Nurse nurse) throws UserCollectionException {
		User resultedNurse = userRepo.findByEmail(nurse.getEmail());
		Nurse newNurse = null;
		User newUser = new User();
		if (resultedNurse == null) {
			nurse.setRegisteredDate(new Date());
			newNurse = nurseRepo.saveAndFlush(nurse);
			newUser.setId(newNurse.getId());
			newUser.setName(nurse.getName());
			newUser.setUserRole(newNurse.getIsHead().equals(true) ? "headnurse" : "nurse");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
			newUser.setEmail(newNurse.getEmail());
			newUser.setStatus(true);
			newUser.setLoginStatus(false);
			newUser.setRegisteredDate(new Date());
			userRepo.save(newUser);
			return newNurse;
		} else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}


	public List<Nurse> getAllNurses() {
		List<Nurse> nurses = nurseRepo.findAllNurses();
		return nurses;
	}

	public List<Nurse> getNurseProfileById(Integer id) {
		List<Nurse> nurse = nurseRepo.getNurseProfileById(id);
		return nurse;

	}

	public List<Nurse> getNurseProfileByName(String name) {
		List<Nurse> nurse = nurseRepo.findByFirstNameLike(name);
		return nurse;
	}

	public List<Nurse> getNurseProfileByClinic(Integer clinicId) {
		List<Nurse> nurse = nurseRepo.findByClinicId(clinicId);
		return nurse;
	}

	public Nurse getNurse(Integer id) {
		return nurseRepo.findById(id).get();
	}

	public List<ClinicAppointment> getQueue(Integer clinic_did) {
		return clinicAppointmentRepo.findByClinicDateId(clinic_did);
	}

	public ClinicDate getClinicDate(Integer id) throws ParseException {
		String date_string = "13-09-2021";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(date_string);

		//Date date = new Date();

		return clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(id, date);
	}

	public Integer getCurrQueueNo(Integer id) {
		return clinicDateRepo.getCurrQueueNo(id);
	}

	public PatientClinicProfile getClinicProfile(Integer id, Integer cid) {
		return patientClinicProfileRepo.findFirstByPatientIdAndClinicId(id, cid);
	}

	public Boolean startClinic(Integer id) {
		Date date = new Date();
		Time time = new Time(date.getTime());

		ClinicDate clinicDate = clinicDateRepo.findById(id).get();

		clinicDate.setStarted(true);
		clinicDate.setStartTime(time);
		clinicDateRepo.save(clinicDate);

		return true;
	}

	public Boolean skipPatient(Integer id){
		ClinicDate clinicDate = clinicDateRepo.findById(id).get();

		if(clinicDate.getNoPatients() > clinicDate.getCurrQueue()){
		clinicDate.setCurrQueue(clinicDate.getCurrQueue() + 1);
		clinicDateRepo.saveAndFlush(clinicDate);
		}

		return true;
	}

	public Boolean endClinic(Integer id){
		Date date = new Date();
		Time time = new Time(date.getTime());

		ClinicDate clinicDate = clinicDateRepo.findById(id).get();

		if(!clinicDate.getEnded()){
			clinicDate.setStarted(false);
			clinicDate.setEnded(true);
			clinicDate.setEndTime(time);

			clinicDateRepo.save(clinicDate);
		}

		return true;
	}

}
