package wecare.backend.exception;

public class ClinicDateException extends Exception{
    public  ClinicDateException(String message){
        super(message);
    }
    public static String NotFoundExeption() {
        return "Clinic Date not found";
    }

}
