package Model;

public enum Role {
    Killer(1, ColorOfGame.Non.name()),
    Robber(2, ColorOfGame.Non.name()),
    Magician(3, ColorOfGame.Non.name()),
    King(4, ColorOfGame.Golden.name()),
    Doctor(5, ColorOfGame.Blue.name()),
    Trader(6, ColorOfGame.Green.name()),
    Architect(7, ColorOfGame.Non.name()),
    Commander(8, ColorOfGame.Red.name());

    private int number;
    private String color;
    private boolean isDead, isRobbed;

    Role(int number, String color) {
        this.number = number;
        this.color = color;
        isDead = false;
        isRobbed = false;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isRobbed() {
        return isRobbed;
    }
}

enum ColorOfGame {
    Non,
    Golden,
    Blue,
    Green,
    Red,
    Purple,
}
