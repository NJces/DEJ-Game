package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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

    public static void initRoles(ArrayList<Role> roles) {
        for (Role role : Role.values()) {
            roles.add(role);
        }
    }

    public static void randomShuffling(ArrayList<Role> roles) {
        Random rand = new Random();  // Random value generator
        for (int i=0; i<roles.size(); i++) {
            int randomIndex = rand.nextInt(roles.size());
            Role temp = roles.get(i);
            roles.set(i, roles.get(randomIndex));
            roles.set(randomIndex, temp);
        }
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
