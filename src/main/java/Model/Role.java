package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public enum Role {
    Killer(1, ColorOfGame.Non.name(), "آدمکش"),
    Robber(2, ColorOfGame.Non.name(), "راهزن"),
    Magician(3, ColorOfGame.Non.name(), "تردست"),
    King(4, ColorOfGame.Golden.name(), "شاه"),
    Doctor(5, ColorOfGame.Blue.name(), "حکیم"),
    Trader(6, ColorOfGame.Green.name(), "تاجر"),
    Architect(7, ColorOfGame.Non.name(), "معمار"),
    Commander(8, ColorOfGame.Red.name(), "سردار");

    private int number;
    private String color, farsiName;
    private boolean isDead, isRobbed, isMagic;

    Role(int number, String color, String farsiName) {
        this.number = number;
        this.color = color;
        this.farsiName = farsiName;
        isDead = false;
        isRobbed = false;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public String getFarsiName() {
        return farsiName;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isRobbed() {
        return isRobbed;
    }

    public boolean isMagic() {return isMagic; }

    public void kill() {
        isDead = true;
    }

    public void steal() {
        isRobbed = true;
    }

    public void magic() {
        isMagic = true;
    }

    public void returnAll() {
        isMagic = false;
        isRobbed = false;
        isDead = false;
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

    public static Role nextRole(Role curentRole) {
        if (curentRole.number < 8) {
            for (Role role : Role.values()) {
                if (role.number == curentRole.number) {
                    return role;
                }
            }
        }
        return null;
    }

    public static Role findByEnglishName(String name) {
        for (Role role : Role.values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }
        return null;
    }

    public static Role findByFarsiName(String name) {
        for (Role role : Role.values()) {
            if (role.farsiName.equals(name)) {
                return role;
            }
        }
        return null;
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
