package wecare.backend.service;


import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import net.bytebuddy.utility.RandomString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.*;
import wecare.backend.repository.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
	
	public Doctor addDoctor(Doctor doctor) throws UserCollectionException, MessagingException, UnsupportedEncodingException {
		Doctor resultDoctor=doctorRepo.findByEmail(doctor.getEmail());
		Doctor newDoctor = null;
		User newUser = new User();
		if(resultDoctor==null) {
//			doctor.getDoctorSchedules();
			newDoctor = doctorRepo.saveAndFlush(doctor);

			String verificationString = RandomString.make(64);

			newUser.setId(newDoctor.getId());
			newUser.setUserRole("doctor");
			newUser.setVerificationString(verificationString);
			newUser.setVerified(false);
			newUser.setPassword("");
			newUser.setEmail(newDoctor.getEmail());

			if(userRepo.save(newUser) != null){
				sendVerificationEmail(newDoctor, newUser);
			}

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
				+ "<h4><href=\'[[link]]\'>[[link]]</h4>"
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
		List<Doctor> doctors =doctorRepo.findAll();
		return doctors;
	}

	public List<ClinicSchedule> getDoctorScheduleById(Integer id){
		List<ClinicSchedule> doctorSchedule = clinicScheduleRepo.getClinicShedule(id);
		return doctorSchedule;
	}
	
	public List<Doctor> getDoctorProfileById(Integer id) {
		List<Doctor> doctor = doctorRepo.getDoctorProfileById(id);
		return doctor;
	
	}
	
	public List<Doctor> getDoctorProfileByName(String name){
		List<Doctor> doctor=doctorRepo.findByFirstNameLike(name);
		return doctor;
	}

	public List<Doctor> getDoctorProfileByClinic(Integer clinicId){
		List<Doctor> doctor=doctorRepo.findByClinicId(clinicId);
		return doctor;
	}
	
	public void deleteDoctorScheduleById(Integer doctorId) {
		LOG.info("START : doctor Id  service {}",doctorId);
		doctorScheduleRepo.deleteDoctorScheduleById(doctorId);
	}
	
	public List<DoctorSchedule> updateDoctorSchedule(List<DoctorSchedule> doctorScheduleList) {	
		List<DoctorSchedule> doctorschedule = doctorScheduleRepo.saveAllAndFlush(doctorScheduleList);
		return doctorschedule;
	
	}

	public List<PatientClinicProfile> getPatientList(Integer clinic){
		return patientClinicProfileRepo.findByClinicId(clinic);
	}

}
