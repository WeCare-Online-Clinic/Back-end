package wecare.backend.exception;

public class ClinicAppointmentException extends Exception{

    public  ClinicAppointmentException(String message){
        super(message);
    }
    public static String NotFoundExeption() {
        return "Clinic Appointment not found";
    }
}
