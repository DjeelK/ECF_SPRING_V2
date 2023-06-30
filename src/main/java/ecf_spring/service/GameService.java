package ecf_spring.service;

import ecf_spring.entity.Player;

public class GameService {
    private Player winner;

    public void setWinner(Player player) {
        if (player.isAdmin()) {
            winner = player;
        } else {
            throw new IllegalArgumentException("Seuls les administrateurs peuvent changer le vainqueur.");
        }
    }
    public Player getWinner() {
        return winner;
    }
}