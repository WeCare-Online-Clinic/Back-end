package wecare.backend.exception;

public class UserCollectionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public  UserCollectionException(String message){
		super(message);
	}

	public static String NotFoundExeption(String id) {
		return "User with "+id +" is not found";
	}

	public static String UserAlreadyExist() {
		return "email is alreay existing";
	}
}
