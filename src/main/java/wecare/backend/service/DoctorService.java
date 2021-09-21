package wecare.backend.service;


import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import net.bytebuddy.utility.RandomString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import wecare.backend.exception.ClinicDateException;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.*;
import wecare.backend.model.dto.ClinicSummary;
import wecare.backend.repository.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import wecare.backend.exception.ClinicAppointmentException;

@Service
public class DoctorService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DoctorService.class);
	
	@Autowired
	private DoctorRepository doctorRepo;	
	
	@Autowired
	private DoctorSchedulesRepository doctorScheduleRepo;
	
	@Autowired
	private ClinicScheduleRepository clinicScheduleRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PatientClinicProfileRepository patientClinicProfileRepo;

	@Autowired
	private ClinicDateRepository clinicDateRepo;

	@Autowired
	private ClinicAppointmentRepository clinicAppointmentRepo;

	@Autowired
	private PatientClinicDataRepository patientClinicDataRepo;

	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private PatientMessageRepository patientMessageRepo;
	
	public Doctor addDoctor(Doctor doctor) throws UserCollectionException, MessagingException, UnsupportedEncodingException {
		User resultDoctor=userRepo.findByEmail(doctor.getEmail());
		Doctor newDoctor = null;
		User newUser = new User();
		if(resultDoctor==null) {
//			doctor.getDoctorSchedules();
			doctor.setRegisteredDate(new Date());
			newDoctor = doctorRepo.saveAndFlush(doctor);

			String verificationString = RandomString.make(64);

			newUser.setId(newDoctor.getId());
			newUser.setUserRole("doctor");
            newUser.setName(doctor.getName());
			newUser.setVerificationString(verificationString);
			newUser.setVerified(false);
			newUser.setPassword("");
			newUser.setEmail(newDoctor.getEmail());
			newUser.setRegisteredDate(new Date());
			newUser.setLoginStatus(false);
			newUser.setStatus(true);

			userRepo.save(newUser);
			sendVerificationEmail(newDoctor, newUser);

			return newDoctor;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}

	public void sendVerificationEmail(Doctor newDoctor, User newUser) throws MessagingException, UnsupportedEncodingException {

		String toAddress = newDoctor.getEmail();
		String fromAddress = "wecare.hospitals.info@gmail.com";
		String senderName = "WeCare Hospitals";
		String subject = "Please verify email and finish registration";
		String body = "Dr. [[name]], <br>"
				+ "Please click the link below to proceed to setting up the account. <br>"
				+ "<h4><href='[[link]]'>[[link]]</h4>"
				+ "Thank you, <br>"
				+ "Wecare Hospitals";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		String link = "localhost:3000/setup/account/"+newUser.getId()+"/"+newUser.getVerificationString();

		body = body.replace("[[name]]", newDoctor.getName());
		body = body.replace("[[link]]", link);
		helper.setText(body, true);

		mailSender.send(message);

	}

	public Doctor getDoctor(Integer id){
		return doctorRepo.findById(id).get();
	}

	public List<Doctor> getAllDoctors(){
		return doctorRepo.findAllDoctors();
	}

	public List<ClinicSchedule> getDoctorScheduleById(Integer id){
		return clinicScheduleRepo.getClinicShedule(id);
	}
	
	public List<Doctor> getDoctorProfileById(Integer id) {
		return doctorRepo.getDoctorProfileById(id);
	}
	
	public List<Doctor> getDoctorProfileByName(String name){
		return  doctorRepo.findByFirstNameLike(name);
	}

	public List<Doctor> getDoctorProfileByClinic(Integer clinicId){
		return doctorRepo.findByClinicId(clinicId);
	}
	


	public List<PatientClinicProfile> getPatientList(Integer clinic){
		return patientClinicProfileRepo.findByClinicId(clinic);
	}

	public List <ClinicDate> getClinicDates(Integer clinic){
		return clinicDateRepo.findByClinicSchedule_ClinicId(clinic);
	}

	public List <ClinicAppointment> getQueue(Integer clinic_did){
		return  clinicAppointmentRepo.findByClinicDateId(clinic_did);
	}

	public PatientClinicData getPatientClinicData(Integer id){
		Date date = new Date();
		return patientClinicDataRepo.findTopByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThanOrderByClinicAppointment_ClinicDateDateDesc(id, date);
	}

	public List<PatientClinicData> getPatientClinicDataList(Integer id){
		Date date = new Date();
		return patientClinicDataRepo.findAllByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(id, date);
	}

	public ClinicDate getClinicDate(Integer id) throws ParseException {
		String date_string = "28-09-2021";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(date_string);

		//Date date = new Date();

		return clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(id, date);
	}

	public Integer getCurrQueueNo(Integer id){
		return clinicDateRepo.getCurrQueueNo(id);
	}

	public PatientClinicProfile getClinicProfile(Integer id, Integer cid){
		return patientClinicProfileRepo.findFirstByPatientIdAndClinicId(id, cid);
	}

	public Boolean addClinicData(PatientClinicData clinicData, Date nextDate, Integer pid, Integer did, Integer cid) throws ParseException, ClinicAppointmentException, ClinicDateException {

		Date date = new Date();
		Time time = new Time(date.getTime());

		ClinicDate clinicDate = clinicDateRepo.findById(did).get();
		if(clinicDate == null){
			throw new ClinicDateException(ClinicDateException.NotFoundExeption());
		}
		else{
			if(clinicDate.getNoPatients() > clinicDate.getCurrQueue()) {
				clinicDate.setCurrQueue(clinicDate.getCurrQueue() + 1);
				clinicDateRepo.saveAndFlush(clinicDate);
			}
			else{
				clinicDate.setEnded(true);
			}
		}

		if(clinicDate.getCurrQueue() + 2 < clinicDate.getNoPatients()){
			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();
			ClinicAppointment nextClinicAppointment = clinicAppointmentRepo.findFirstByClinicDateIdAndQueueNo(clinicDate.getId(), clinicDate.getCurrQueue() + 2);
			if(nextClinicAppointment != null){
				Patient nextPatient = nextClinicAppointment.getPatient();
				PatientMessage patientMessage = new PatientMessage();
				patientMessage.setPatient(nextPatient);
				patientMessage.setDate(localDate);
				patientMessage.setTime(localTime);
				patientMessage.setMessage("Please visit consultation room for clinic appointment. Current queue no: " + clinicDate.getCurrQueue());
				patientMessageRepo.save(patientMessage);
			}
		}

		ClinicAppointment clinicAppointment = clinicAppointmentRepo.findFirstByPatientIdAndClinicDateId(pid, did);
		if(clinicAppointment == null){
			throw new ClinicAppointmentException(ClinicAppointmentException.NotFoundExeption());
		}
		else{
			clinicData.setClinicAppointment(clinicAppointment);
			patientClinicDataRepo.saveAndFlush(clinicData);

			clinicAppointment.setVisited(true);
			clinicAppointment.setTime(time);

			if(clinicAppointment.getQueueNo() != 1){
				if(clinicAppointment.getQueueNo().equals(clinicDate.getNoPatients())){
					List<ClinicAppointment> clinicAppointments = clinicAppointmentRepo.findByClinicDateIdAndVisited(did, true);
					Integer visitedPatients = clinicAppointments.size();
					clinicDate.setEndTime(time);
					clinicDate.setEnded(true);
					clinicDate.setStarted(false);
					clinicDate.setVisitedPatients(visitedPatients);
				}
			}
		}

		ClinicDate nextClinicDate = clinicDateRepo.findFirstByClinicSchedule_ClinicIdAndDate(cid, nextDate);


		if(nextClinicDate != null){
			ClinicAppointment available = clinicAppointmentRepo.findByPatientIdAndClinicDateId(clinicAppointment.getPatient().getId(), nextClinicDate.getId());

			if(available != null){
				return true;
			}
			Time scheduleTime = nextClinicDate.getClinicSchedule().getTime();
			LocalTime localTime = scheduleTime.toLocalTime();

			List<Integer> queue = nextClinicDate.getQueue();
			queue.add(pid);
			nextClinicDate.setQueue(queue);
			nextClinicDate.setNoPatients(nextClinicDate.getNoPatients() + 1);
			clinicDateRepo.saveAndFlush(nextClinicDate);

			ClinicAppointment newClinicAppointment = new ClinicAppointment();
			newClinicAppointment.setQueueNo(nextClinicDate.getNoPatients());
			newClinicAppointment.setPatient(clinicAppointment.getPatient());
			newClinicAppointment.setClinicDate(nextClinicDate);
			newClinicAppointment.setVisited(false);

			localTime = localTime.plusMinutes(5L * nextClinicDate.getNoPatients());

			newClinicAppointment.setTime(Time.valueOf(localTime));
			clinicAppointmentRepo.saveAndFlush(newClinicAppointment);
			if(newClinicAppointment.getPatient().getContact() != null){
				String message = "New clinic appointment on " + newClinicAppointment.getClinicDate().getDate() +
						". Queue No: " + newClinicAppointment.getQueueNo() + "Time: " + newClinicAppointment.getTime();
				sendSms(newClinicAppointment.getPatient().getContact(), message);
			}

		}
		else{
			Calendar c = Calendar.getInstance();
			c.setTime(nextDate);
			DateFormat weekFormatter = new SimpleDateFormat("EEEE");

			ClinicDate newClinicDate = new ClinicDate();
			newClinicDate.setDate(nextDate);
			List<Integer> queue = new ArrayList<Integer>();
			queue.add(pid);
			newClinicDate.setQueue(queue);

			ClinicSchedule clinicSchedule = clinicScheduleRepo.findByClinicIdAndDay(cid, weekFormatter.format(nextDate));
			newClinicDate.setClinicSchedule(clinicSchedule);
			newClinicDate.setNoPatients(1);
			newClinicDate.setStarted(false);
			newClinicDate.setEnded((false));
			newClinicDate.setCurrQueue(1);
			newClinicDate = clinicDateRepo.saveAndFlush(newClinicDate);

			Time scheduleTime =newClinicDate.getClinicSchedule().getTime();
			LocalTime localTime = scheduleTime.toLocalTime();

			ClinicAppointment newClinicAppointment = new ClinicAppointment();
			newClinicAppointment.setQueueNo(1);
			newClinicAppointment.setTime(scheduleTime);
			newClinicAppointment.setPatient(clinicAppointment.getPatient());
			newClinicAppointment.setClinicDate(newClinicDate);
			newClinicAppointment.setVisited(false);
			clinicAppointmentRepo.saveAndFlush(newClinicAppointment);
			if(newClinicAppointment.getPatient().getContact() != null){
				String message = "New clinic appointment on " + newClinicAppointment.getClinicDate().getDate() +
						". Queue No: " + newClinicAppointment.getQueueNo() + "Time: " + newClinicAppointment.getTime();
				sendSms(newClinicAppointment.getPatient().getContact(), message);
			}
		}

		PatientClinicProfile clinicProfile = patientClinicProfileRepo.findFirstByPatientIdAndClinicId(pid, cid);
		if(!clinicProfile.getDiagnosis().equals(clinicData.getDiagnosis())){
			clinicProfile.setDiagnosis(clinicData.getDiagnosis());
			patientClinicProfileRepo.save(clinicProfile);
		}

		return true;
	}

	public ClinicSummary getClinicSummary(Integer id) throws ParseException {
		ClinicDate clinicDate = getClinicDate(id);
		List<ClinicAppointment> visitedPatients = clinicAppointmentRepo.findByClinicDateIdAndVisited(clinicDate.getId(), true);
		List<ClinicAppointment> notVisitedPatients = clinicAppointmentRepo.findByClinicDateIdAndVisited(clinicDate.getId(), false);

		ClinicSummary clinicSummary = new ClinicSummary();
		clinicSummary.setClinicDate(clinicDate);
		clinicSummary.setVisitedPatients(visitedPatients);
		clinicSummary.setNotVisitedPatients(notVisitedPatients);

		return  clinicSummary;
	}

	public void sendSms(Integer phoneNumber, String message){

		Twilio.init("AC7259622d5c4317798e7587a3a2bb72fd", "fce387d1a0fc715f83423a0fef20690d");
		String number = "+94"+phoneNumber;
		com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(number), new PhoneNumber("+13038163922"), message).create();

	}

    public List<Report> getReports(Integer pid, Integer cid) {
		return reportRepo.findByPatientIdAndTestClinicId(pid, cid);
    }

	public Report getRecentReport(Integer pid, Integer cid) {
		return reportRepo.findTopByPatientIdAndTestClinicIdOrderByIssuedDate(pid, cid);
	}
}
