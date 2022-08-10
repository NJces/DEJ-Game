package Model;

import java.util.ArrayList;

public class Player {
    private String name, code;
    private static int number = 0;
    private int age;
    private Role[] roles;
    private ArrayList<StructureCard> cityStructures = new ArrayList<>();
    private Source source;

    public Player() {
        code = "#" + (++number);
    }

    public void init(String name, int age) {
        this.name = name;
        this.age = age;


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

    public void setSource(int coins, ArrayList<StructureCard> cards) {
        this.source = new Source(coins, cards);
    }


    private class Source {
        private int coins;
        private ArrayList<StructureCard> structureCards = new ArrayList<>();

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

    public void structCard(StructureCard card) {
        cityStructures.add(card);
        source.removeCards(card);
    }
}
