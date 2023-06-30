package ecf_spring.service;

import ecf_spring.entity.AppUser;
import ecf_spring.entity.Partie;
import ecf_spring.entity.Tournoi;
import ecf_spring.exception.*;
import ecf_spring.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PartieService {
    @Autowired
    private PartieRepository partieRepository;
    @Autowired
    private LoginService loginService;

    public boolean savePartie(Tournoi tournoi, AppUser user1, AppUser user2, String datePartie, String heurePartie) throws PartieExistException, EmptyFieldsException, NotAdminException, NotSignInException {
        if (loginService.isLogged()) {
            if (loginService.isAdmin()) {
                if (tournoi != null && user1 != null && user2 != null) {
                    Partie partie = Partie.builder()
                            .tournoi(tournoi)
                            .user1(user1)
                            .user2(user2)
                            .datePartie(datePartie)
                            .heurePartie(heurePartie)
                            .build();
                    if (partieRepository.existsByTournoiAndUser1IdAndUser2Id(tournoi, user1.getId(), user2.getId())){
                        throw new PartieExistException();
                    }
                    partieRepository.save(partie);
                    return true;
                }
                throw new EmptyFieldsException("tournoi, user1, user2");
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public List<Partie> getParties() throws NotSignInException {
        if (loginService.isLogged()) {
            return (List<Partie>) partieRepository.findAll();
        }
        throw new NotSignInException();
    }

    public Partie getPartieById(int id) throws NotSignInException, PartieNotExistException {
        if(loginService.isLogged()) {
            try {
                return partieRepository.findById(id).orElseThrow(PartieNotExistException::new);
            } catch (Exception ex) {
                throw new PartieNotExistException();
            }
        }
        throw new NotSignInException();
    }

    public boolean updatePartie(int id) throws NotAdminException, NotSignInException{
        if (loginService.isLogged()) {
            if (loginService.isAdmin()) {
                Partie partie = partieRepository.findById(id).get();
                partieRepository.save(partie);
                return true;
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }
}
