package ecf_spring.service;
import ecf_spring.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameServiceTest {
    private GameService gameService;
    private Player adminPlayer;
    private Player regularPlayer;

    @BeforeEach
    public void setup() {
        gameService = new GameService();
        adminPlayer = new Player("Admin", true);
        regularPlayer = new Player("Regular", false);
    }

    @Test
    public void testSetWinner_AdminPlayer_Success() {
        gameService.setWinner(adminPlayer);
        Player winner = gameService.getWinner();
        Assertions.assertEquals(adminPlayer, winner);
    }

    @Test
    public void testSetWinner_RegularPlayer_ThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameService.setWinner(regularPlayer);
        });
    }
}