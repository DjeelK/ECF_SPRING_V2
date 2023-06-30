package ecf_spring.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testIsAdmin() {
        Player player = new Player("John", true);
        Assertions.assertTrue(player.isAdmin());
    }
}