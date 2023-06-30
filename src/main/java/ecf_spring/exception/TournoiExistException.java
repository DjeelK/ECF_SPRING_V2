package ecf_spring.exception;

public class TournoiExistException extends Exception{
    public TournoiExistException(){
        super("Tournoi Exist");
    }
}
