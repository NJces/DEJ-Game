package Model;

import java.util.ArrayList;

public class Player {
    private String name, code;
    private static int number = 0;
    private int age;
    //private Role[] roles;
    //private ArrayList<CityStructure> cityStructures = new ArrayList<CityStructure>();
    private Source source;

    public Player() {
        code = "#" + (++number);
    }

    public void init(String name, int age) {
        this.name = name;
        this.age = age;

        //At the beginning of the game, each player gets 2 coins and 4 cards in a random.
        //source = new Source(2, Game.getStructureCards(4));
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return  code;
    }

    public int getAge() {
        return age;
    }


    private class Source {
        private int coins;
        //private ArrayList<StructureCard> structureCards = new ArrayList<StructureCard>();
        /*
        public Source(int coins, StructureCard...structureCards) {
            this.coins = coins;
            this.structureCards = structureCards;
        }
         */

        private void addCoins(int number) {
            coins += number;
        }

        private void subtractCoins(int number) {
            coins -= number;
        }

        private int stolenCoins() {
            int curentCoins = coins;
            coins = 0;
            return curentCoins;
        }

        //add card
        //subtract card
        //stolen/change cards

    }
}
