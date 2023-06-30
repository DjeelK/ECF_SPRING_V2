package ecf_spring.entity;

public class Player {
    private String name;
    private int score;
    private boolean isAdmin;

    public Player(String name, boolean isAdmin) {
        this.name = name;
        this.score = score;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
