package ecf_spring.exception;

public class TournoiNotExistException extends Exception {
    public TournoiNotExistException(){
        super("Tournoi not Exist");
    }
}
