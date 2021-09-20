package wecare.backend.service;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.*;
import wecare.backend.model.dto.*;
import wecare.backend.model.dto.Message;
import wecare.backend.repository.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

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

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private ClinicScheduleRepository clinicScheduleRepo;

	@Autowired
	private PatientRequestRepository patientRequestRepo;

	@Autowired
	private ClinicRepository clinicRepo;

	@Autowired
	private ClinicMessageRepository clinicMessageRepo;

	@Autowired
	private ClinicDateMessageRepository clinicDateMessageRepo;

	@Autowired PatientMessageRepository patientMessageRepo;

	@Autowired
	private JavaMailSender mailSender;

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
		//String date_string = "17-09-2021";
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//Date date = formatter.parse(date_string);

		Date date = new Date();

		return clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(id, date);
	}

	public Integer getCurrQueueNo(Integer id) {
		return clinicDateRepo.getCurrQueueNo(id);
	}

	public PatientClinicProfile getClinicProfile(Integer id, Integer cid) {
		return patientClinicProfileRepo.findFirstByPatientIdAndClinicId(id, cid);
	}

	public Boolean startClinic(Integer id, Integer nurseId) {
		Date date = new Date();
		Time time = new Time(date.getTime());

		ClinicDate clinicDate = clinicDateRepo.findById(id).get();

		if (clinicDate.getStarted() == null || !clinicDate.getStarted()) {
			clinicDate.setStarted(true);
			clinicDate.setStartTime(time);
			clinicDate.setNurse(nurseRepo.findById(nurseId).get());
			clinicDateRepo.save(clinicDate);
		}

		return true;
	}

	public Boolean skipPatient(Integer id) {
		ClinicDate clinicDate = clinicDateRepo.findById(id).get();

		if (clinicDate.getNoPatients() > clinicDate.getCurrQueue()) {
			clinicDate.setCurrQueue(clinicDate.getCurrQueue() + 1);
			clinicDateRepo.saveAndFlush(clinicDate);
		}

		return true;
	}

	public Boolean endClinic(Integer id) {
		Date date = new Date();
		Time time = new Time(date.getTime());

		ClinicDate clinicDate = clinicDateRepo.findById(id).get();
		List<ClinicAppointment> clinicAppointments = clinicAppointmentRepo.findByClinicDateIdAndVisited(clinicDate.getId(), true);
		Integer visitedPatients = clinicAppointments.size();

		if (clinicDate.getEnded() == null || !clinicDate.getEnded()) {
			clinicDate.setStarted(false);
			clinicDate.setEnded(true);
			clinicDate.setEndTime(time);
			clinicDate.setVisitedPatients(visitedPatients);
			clinicDateRepo.save(clinicDate);
		}

		return true;
	}

	public CheckPatient checkPatient(String nic, Integer cid) {
		CheckPatient checkPatient = new CheckPatient();
		Patient patient = patientRepo.findByNic(nic);
		checkPatient.setPatient(patient);

		if (patient != null) {
			PatientClinicProfile patientClinicProfile = patientClinicProfileRepo.findFirstByPatientIdAndClinicId(patient.getId(), cid);
			checkPatient.setPatientClinicProfile(patientClinicProfile);
		} else {
			checkPatient.setPatientClinicProfile(null);
		}

		return checkPatient;
	}

	public Boolean addPatient(PatientRegister patientRegister) throws MessagingException, UnsupportedEncodingException {

		LocalDate date = LocalDate.now();
		Date date1 = new Date();

		CheckPatient checkPatient = checkPatient(patientRegister.getPatient().getNIC(), patientRegister.getClinic().getId());
		Integer lastPatient = patientRepo.findTopByOrderByIdDesc().getId();
		Integer lastProfile = patientClinicProfileRepo.findTopByOrderByIdDesc().getId();

		if (checkPatient.getPatient() == null) {
			Patient patient = new Patient();
			patient = patientRegister.getPatient();
			patient.setId(lastPatient + 1);

			User newUser = new User();
			String verificationString = RandomString.make(64);

			newUser.setUserRole("patient");
			newUser.setName(patient.getName());
			newUser.setVerificationString(verificationString);
			newUser.setVerified(false);
			newUser.setPassword("");
			newUser.setEmail(patient.getEmail());
			newUser.setRegisteredDate(new Date());
			newUser.setLoginStatus(false);
			newUser.setStatus(true);

			PatientClinicProfile patientClinicProfile = new PatientClinicProfile();
			patientClinicProfile.setId(lastPatient + 1);
			patientClinicProfile.setClinic(patientRegister.getClinic());
			patientClinicProfile.setAdmissionDate(date);

			Patient patient1 = addData(newUser,patient,patientClinicProfile);

			addClinicAppointment(patient1, patientRegister.getClinic(), date1);
		} else {
			if (checkPatient.getPatientClinicProfile() == null) {
				PatientClinicProfile patientClinicProfile = new PatientClinicProfile();
				Patient patient = checkPatient.getPatient();
				patientClinicProfile.setPatient(patient);
				patientClinicProfile.setClinic(patientRegister.getClinic());
				patientClinicProfile.setAdmissionDate(date);

				patientClinicProfileRepo.save(patientClinicProfile);
				addClinicAppointment(patient, patientRegister.getClinic(), date1);
			}
		}

		return true;
	}

	@Transactional
	public Patient addData(User user, Patient patient, PatientClinicProfile patientClinicProfile) throws MessagingException, UnsupportedEncodingException {
		Patient patient1 = patientRepo.save(patient);
		user.setId(patient1.getId());
		userRepo.save(user);
		patientClinicProfile.setPatient(patient1);
		patientClinicProfileRepo.save(patientClinicProfile);

		sendVerificationEmail(patient1,  user);

		return patient1;
	}

	public ClinicAppointment addClinicAppointment(Patient patient, Clinic clinic, Date date) {

		ClinicDate nextClinicDate = clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(clinic.getId(), date);
		ClinicAppointment newClinicAppointment = new ClinicAppointment();

		if (nextClinicDate != null) {
			Time scheduleTime = nextClinicDate.getClinicSchedule().getTime();
			LocalTime localTime = scheduleTime.toLocalTime();

			List<Integer> queue = nextClinicDate.getQueue();
			queue.add(patient.getId());
			nextClinicDate.setQueue(queue);
			nextClinicDate.setNoPatients(nextClinicDate.getNoPatients() + 1);
			clinicDateRepo.saveAndFlush(nextClinicDate);

			localTime = localTime.plusMinutes(5L * nextClinicDate.getNoPatients());

			newClinicAppointment.setTime(Time.valueOf(localTime));
			newClinicAppointment.setQueueNo(nextClinicDate.getNoPatients());
			newClinicAppointment.setPatient(patient);
			newClinicAppointment.setClinicDate(nextClinicDate);
			newClinicAppointment.setVisited(false);
			clinicAppointmentRepo.saveAndFlush(newClinicAppointment);
		} else {

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			DateFormat weekFormatter = new SimpleDateFormat("EEEE");

			ClinicDate newClinicDate = new ClinicDate();
			newClinicDate.setDate(date);
			List<Integer> queue = new ArrayList<Integer>();
			queue.add(patient.getId());
			newClinicDate.setQueue(queue);

			ClinicSchedule clinicSchedule = clinicScheduleRepo.findByClinicIdAndDay(clinic.getId(), weekFormatter.format(date));
			newClinicDate.setClinicSchedule(clinicSchedule);
			newClinicDate.setNoPatients(1);
			newClinicDate.setStarted(false);
			newClinicDate.setEnded((false));
			newClinicDate.setCurrQueue(1);
			ClinicDate newClinicDate1 = clinicDateRepo.saveAndFlush(newClinicDate);
			Time scheduleTime = newClinicDate1.getClinicSchedule().getTime();
			LocalTime localTime = scheduleTime.toLocalTime();

			newClinicAppointment.setQueueNo(1);
			newClinicAppointment.setTime(scheduleTime);
			newClinicAppointment.setPatient(patient);
			newClinicAppointment.setClinicDate(newClinicDate1);
			newClinicAppointment.setVisited(false);
			clinicAppointmentRepo.saveAndFlush(newClinicAppointment);
		}

		return newClinicAppointment;
	}

	public void sendVerificationEmail(Patient patient, User newUser) throws MessagingException, UnsupportedEncodingException {

		String toAddress = patient.getEmail();
		String fromAddress = "wecare.hospitals.info@gmail.com";
		String senderName = "WeCare Hospitals";
		String subject = "Please verify email and finish registration";
		String body = "Mr/Mrs. [[name]], <br>"
				+ "Please click the link below to proceed to setting up the account. <br>"
				+ "<h4><href='[[link]]'>[[link]]</h4>"
				+ "Thank you, <br>"
				+ "Wecare Hospitals";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		String link = "localhost:3000/setup/account/" + newUser.getId() + "/" + newUser.getVerificationString();

		body = body.replace("[[name]]", patient.getName());
		body = body.replace("[[link]]", link);
		helper.setText(body, true);

		mailSender.send(message);
		sendSms(patient.getContact(),body);
	}

	public void sendAppointmentEmail(ClinicAppointment clinicAppointment) throws MessagingException, UnsupportedEncodingException {

		String toAddress = clinicAppointment.getPatient().getEmail();
		String fromAddress = "wecare.hospitals.info@gmail.com";
		String senderName = "WeCare Hospitals";
		String subject = "Clinic Appointment";
		String body = "Mr/Mrs. [[name]], <br>"
				+ "New clinic appointment information <br>"
				+ "Clinic Date: [[date]] <br>"
				+ "Queue No: [[no]] <br>"
				+ "Time: [[time]] <br>"
				+ "Thank you, <br>"
				+ "Wecare Hospitals";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		body = body.replace("[[name]]", clinicAppointment.getPatient().getName());
		body = body.replace("[[date]]", clinicAppointment.getClinicDate().getDate());
		body = body.replace("[[no]]", toString(clinicAppointment.getQueueNo()));
		body = body.replace("[[time]]", clinicAppointment.getTime());
		helper.setText(body, true);

		mailSender.send(message);
		sendSms(clinicAppointment.getPatient().getContact(), body);
	}

	private String toString(Integer queueNo) {
		return ""+queueNo;
	}

	public RequestChange getPatientRequest(Integer cid) {
		Date date = new Date();
		RequestChange requestChange = new RequestChange();

		 requestChange.setRequestList(patientRequestRepo.findByClinicIdAndChangedAndClinicDateDateGreaterThan(cid, false, date));
		 requestChange.setAvailableDates(clinicDateRepo.findByClinicSchedule_ClinicIdAndDateGreaterThan(cid, date));

		 return requestChange;
	}

	@Transactional
	public Boolean changeAppointment(ChangeAppointment obj) throws MessagingException, UnsupportedEncodingException {

		ClinicAppointment available = clinicAppointmentRepo.findByPatientIdAndClinicDateDate(obj.getPatientRequest().getPatient().getId(), obj.getDate());

		if(available != null){
			return false;
		}
		else {
			PatientRequest patientRequest = obj.getPatientRequest();
			ClinicAppointment clinicAppointment = addClinicAppointment(patientRequest.getPatient(), patientRequest.getClinic(), obj.getDate());
			ClinicDate clinicDate = patientRequest.getClinicDate();
			List<Integer> queue = clinicDate.getQueue();
			queue.remove(new Integer(patientRequest.getPatient().getId()));
			clinicDate.setQueue(queue);
			clinicDate.setNoPatients(clinicDate.getNoPatients() - 1);
			patientRequest.setChanged(true);

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();

			String message = "Clinic Appointment on " + clinicDate.getDate() + "changed to " + clinicAppointment.getClinicDate().getDate();

			PatientMessage patientMessage = new PatientMessage();
			patientMessage.setPatient(patientRequest.getPatient());
			patientMessage.setDate(localDate);
			patientMessage.setTime(localTime);
			patientMessage.setMessage(message);

			patientRequestRepo.save(patientRequest);
			clinicDateRepo.save(clinicDate);
			patientMessageRepo.save(patientMessage);
			clinicAppointmentRepo.deleteByPatientIdAndClinicDateId(patientRequest.getPatient().getId(), patientRequest.getClinicDate().getId());

		}

		return true;
	}

    public List<ClinicDate> getNextClinicDates(Integer id) {
		Date date = new Date();

		return clinicDateRepo.findByClinicSchedule_ClinicIdAndDateGreaterThan(id, date);
    }

	public Boolean setClinicMessage(Message obj) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();

		ClinicMessage clinicMessage = new ClinicMessage();
		clinicMessage.setDate(localDate);
		clinicMessage.setTime(localTime);
		clinicMessage.setMessage(obj.getMessage());
		clinicMessage.setClinic(clinicRepo.findById(obj.getId()).get());
		clinicMessage.setNurse(nurseRepo.findById(obj.getNurseId()).get());

		clinicMessageRepo.save(clinicMessage);

		return true;
	}

	public Boolean setClinicDateMessage(Message obj) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();

		ClinicDateMessage clinicDateMessage = new ClinicDateMessage();
		clinicDateMessage.setDate(localDate);
		clinicDateMessage.setTime(localTime);
		clinicDateMessage.setMessage(obj.getMessage());
		clinicDateMessage.setClinicDate(clinicDateRepo.findById(obj.getId()).get());
		clinicDateMessage.setNurse(nurseRepo.findById(obj.getNurseId()).get());

		clinicDateMessageRepo.save(clinicDateMessage);

		return true;
	}

	public MessageList getMessages(Integer id) {
		MessageList messageList = new MessageList();

		messageList.setClinicMessages(clinicMessageRepo.findAllByClinicIdOrderByDateAsc(id));
		messageList.setClinicDateMessages(clinicDateMessageRepo.findAllByClinicDate_ClinicSchedule_ClinicIdOrderByDateAsc(id));

		return messageList;
	}

	public void sendSms(Integer phoneNumber, String message){

		Twilio.init("AC7259622d5c4317798e7587a3a2bb72fd", "be5bc271833c26d586e4fe7fd88594ca");
		String number = "+94"+phoneNumber;
		com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(number), new PhoneNumber("+13038163922"), message).create();

	}

	public Boolean addNewAppointment(AddAppointment obj) throws MessagingException, UnsupportedEncodingException {

		ClinicAppointment available = clinicAppointmentRepo.findByPatientIdAndClinicDateDate(obj.getPatient().getId(), obj.getDate());

		if(available != null){
			return false;
		}
		else {

			ClinicAppointment clinicAppointment = addClinicAppointment(obj.getPatient(), obj.getClinic(), obj.getDate());
			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();
			String message = "New " + obj.getClinic() + " clinic appointment on: " + clinicAppointment.getClinicDate().getDate()
					+ " Time: " + clinicAppointment.getTime() + " Queue no: " + clinicAppointment.getQueueNo();

			PatientMessage patientMessage = new PatientMessage();
			patientMessage.setPatient(obj.getPatient());
			patientMessage.setDate(localDate);
			patientMessage.setTime(localTime);
			patientMessage.setMessage(message);
			patientMessageRepo.save(patientMessage);
			sendAppointmentEmail(clinicAppointment);
			sendSms(clinicAppointment.getPatient().getContact(), message);
		}

		return true;
	}

    public List<PatientClinicProfile> getPatients(Integer id) {
		return patientClinicProfileRepo.findByClinicId(id);
    }
}
