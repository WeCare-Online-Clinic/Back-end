package wecare.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wecare.backend.model.*;
import wecare.backend.model.dto.UserCount;
import wecare.backend.service.AdminService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getUserCounts")
    public List<UserCount> getUserCounts() {
        List<UserCount> userCount = adminService.getUserCounts();
        return userCount;
    }

    @GetMapping("/changeDoctorStatus/{doctorId}")
    public ResponseEntity<Integer> changeDoctorStatus(@PathVariable Integer doctorId) {
        Integer result = adminService.changeDoctorStatus(doctorId);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/changeNurseStatus/{nurseId}")
    public ResponseEntity<Integer> changeNurseStatus(@PathVariable Integer nurseId) {
        Integer result = adminService.changeNurseStatus(nurseId);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getDoctorProfileByDoctorId/{doctorId}")
    public List<Doctor> getDoctorProfileByDoctorId(@PathVariable String doctorId) {
        List<Doctor> doctor = adminService.getDoctorProfileByDoctorId(doctorId);
        return doctor;

    }
    @GetMapping("/getNurseProfileByNurseId/{nurseId}")
    public List<Nurse> getNurseProfileByNurseId(@PathVariable String nurseId) {
        List<Nurse> nurse = adminService.getNurseProfileByNurseId(nurseId);
        return nurse;

    }

    @DeleteMapping("/deleteDoctorSchedule/{doctorId}")
    public void deleteDoctorScheduleById(@PathVariable Integer doctorId) {
        adminService.deleteDoctorScheduleById(doctorId);
    }

    @PostMapping("/updateDoctorSchedule")
    public String updateDoctorSchedule(@RequestBody List<DoctorSchedule> doctorScheduleList) {
        Integer doctorSchedule = adminService.updateDoctorSchedule(doctorScheduleList);
        if (doctorSchedule.equals(1)) {
            return "update unsuccessfull";
        } else {
            return "successfuly updated";
        }

    }

    @DeleteMapping("/deleteNurseSchedule/{nurseId}")
    public void deleteNurseScheduleById(@PathVariable Integer nurseId) {
        adminService.deleteNurseScheduleById(nurseId);
    }

    @PostMapping("/updateNurseSchedule")
    public String updateNurseSchedule(@RequestBody List<NurseSchedule> nurseSchedulelist) {
       Integer nurseSchedule = adminService.updateNurseSchedule(nurseSchedulelist);
        if (nurseSchedule.equals(1)) {
            return "update unsuccessfull";
        } else {
            return "successfuly updated";
        }
    }

    @GetMapping("/getOnlineUsers")
    public List<User> getOnlineUsers(){
        List<User> users =adminService.getOnlineUsers();
        return users;
    }

    @GetMapping("/getRegisteredUsers")
    public ArrayList<Integer> getRegisteredUsers(){
        ArrayList<Integer> registeredList=adminService.getRegisteredUsers();
        return  registeredList;
    }
    @GetMapping("/admin/info/{id}")
    public Admin getAdminInfo(@PathVariable Integer id){
        return adminService.getAdmin(id);
    }



}
