//package ecf_spring;
//
//import ecf_spring.controller.UserController;
//import ecf_spring.service.AppUserService;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserControllerTest {
//
//    @Test
//    void testSignIn_ValidCredentials() {
//        // Créer une instance de AppUserService (ou utiliser un mock si vous avez déjà implémenté votre service)
//        AppUserService appUserService = new AppUserService();
//
//        // Créer une instance de UserController en passant l'AppUserService dans le constructeur
//        UserController userController = new UserController(appUserService);
//
//        // Définir d'autres dépendances si nécessaire (par exemple, HttpServletResponse)
//
//        // Appeler directement la méthode signIn avec des identifiants valides
//        String email = "john@example.com";
//        String password = "password";
//
//        try {
//            String redirect = userController.signIn(email, password);
//            assertEquals("redirect:/user/userExist?message=Bienvenue", redirect);
//        } catch (IOException e) {
//            fail("Exception not expected");
//        }
//    }
//
//    // Autres tests unitaires pour différents cas de signIn()...
//
//}