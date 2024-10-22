package lk.ijse.aad67.posbackendspring.exceptions;

public class DataPersistException extends RuntimeException{
    public DataPersistException() {}
    public DataPersistException(String message) {
        super(message);
    }
    public DataPersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
