package Model;

import java.util.ArrayList;

public class Player {
    private String name, code;
    private static int number = 0;
    private int age;
    private Role[] roles;
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
        private ArrayList<StructureCard> structureCards = new ArrayList<StructureCard>();

        public Source(int coins, ArrayList<StructureCard> structureCards) {
            this.coins = coins;
            this.structureCards = structureCards;
        }


        public void addCoins(int number) {
            coins += number;
        }

        public void subtractCoins(int number) {
            coins -= number;
        }

        public int stolenCoins() {
            int curentCoins = coins;
            coins = 0;
            return curentCoins;
        }

        public void addCards(StructureCard... cards) {
            for (StructureCard card : cards) {
                structureCards.add(card);
            }
        }

        private void removeCards(StructureCard... cards) {
            for (StructureCard card : cards) {
                structureCards.remove(card);
            }
        }

        public ArrayList<StructureCard> StolenCards() {
            ArrayList<StructureCard> list = structureCards;
            structureCards = new ArrayList<>();
            return list;
        }

        public ArrayList<StructureCard> changedCards(ArrayList<StructureCard> cards) {
            ArrayList<StructureCard> list = structureCards;
            structureCards = cards;
            return list;
        }
    }
}
