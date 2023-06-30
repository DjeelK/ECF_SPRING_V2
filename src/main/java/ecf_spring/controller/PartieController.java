package ecf_spring.controller;

import ecf_spring.entity.AppUser;
import ecf_spring.entity.Partie;
import ecf_spring.entity.Tournoi;
import ecf_spring.exception.*;
import ecf_spring.service.AppUserService;
import ecf_spring.service.PartieService;
import ecf_spring.service.TournoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partie")
public class PartieController {
    @Autowired
    private PartieService partieService;
    @Autowired
    private TournoiService tournoiService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("")
    public ModelAndView getParties() throws NotSignInException {
        ModelAndView mv = new ModelAndView("partie");
        mv.addObject("parties", partieService.getParties());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView formAddPartie() {
        ModelAndView mv = new ModelAndView("partieform");
        return mv;
    }

    @PostMapping("/add")
    public String submitFormAddPartie(int tournoiId, int appUser1Id, int appUser2Id, String datePartie, String heurePartie, Integer vainqueur) throws EmptyFieldsException, NotAdminException, NotSignInException, TournoiNotExistException, PartieExistException, UserNotExistException {
        Tournoi tournoi = tournoiService.getTournoiById(tournoiId);
        AppUser appUser1 = appUserService.getAppUserById1(appUser1Id);
        AppUser appUser2 = appUserService.getAppUserById2(appUser2Id);
        if (partieService.savePartie(tournoi, appUser1, appUser2, datePartie, heurePartie, vainqueur)) {
            return "redirect:/partie";
        }
        return null;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formEditPartie(@PathVariable int id) throws PartieNotExistException, NotSignInException {
        ModelAndView mv = new ModelAndView("editpartieform");
        Partie partie = partieService.getPartieById(id);
        mv.addObject("partie", partie);
        mv.addObject("tournoi", partie.getTournoi());
        mv.addObject("user1", partie.getUser1());
        mv.addObject("user2", partie.getUser2());
        mv.addObject("vainqueur", partie.getVainqueur());
        return mv;
    }
    @PostMapping("/update/{id}")
    public String submitFormUpdatePartie(@ModelAttribute("partie") Partie partie, @RequestParam("vainqueur") Integer vainqueur) {
        try {
            partie.setVainqueur(vainqueur);
            partieService.updateVainqueur(partie.getId(), partie.getVainqueur());
            return "redirect:/partie";
        } catch (PartieNotExistException e) {

        } catch (NotSignInException | NotAdminException e) {

        }
        return null;
    }


    @ExceptionHandler(NotSignInException.class)
    public ModelAndView handleNotSignInException(NotSignInException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(NotAdminException.class)
    public ModelAndView handleNotAdminException(NotAdminException ex) {
        ModelAndView mv = new ModelAndView("editpartieform");
        mv.addObject("errorMessage", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(PartieNotExistException.class)
    public ModelAndView handlePartieNotExistException(PartieNotExistException ex) {
        ModelAndView mv = new ModelAndView("editpartieform");
        mv.addObject("errorMessage", ex.getMessage());
        return mv;
    }
}
