package Model;

import java.util.ArrayList;

public class Player {
    private String name, code;
    private static int number = 0;
    private int age;
    private ArrayList<Role> roles = new ArrayList<>();


    private ArrayList<StructureCard> cityStructures = new ArrayList<>();
    private Source source;
    private boolean crown;

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

    public boolean getCrown() {
        return crown;
    }

    public void setCrown() {
        crown = true;
    }

    public void lossCrown() {
        crown = false;
    }

    public int getNumOfCoins() {
        return source.coins;
    }

    public int getCityStructuresSize() {
        return cityStructures.size();
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

        public ArrayList<StructureCard> stolenCards() {
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
        if (card.getCost() <= source.coins) {
            source.subtractCoins(card.getCost());
            cityStructures.add(card);
            source.removeCards(card);
        }
    }

    public void destructCard(StructureCard card) {
        cityStructures.remove(card);
    }

    public void getCards(StructureCard... cards) {
        source.addCards(cards);
    }

    public ArrayList<StructureCard> stolenCards() {
        return source.stolenCards();
    }

    public ArrayList<StructureCard> changedCards(ArrayList<StructureCard> cards) {
        return source.changedCards(cards);
    }

    public boolean hasRole(Role role) {
        for (Role r : roles) {
            if (r.equals(role)) {
                return true;
            }
        }
        return false;
    }

    public int stolenCoins() {
        return source.stolenCoins();
    }

    public void getCoins(int number) {
        source.addCoins(number);
    }

    public void giveCoins(int number) {
        source.subtractCoins(number);
    }

    public void setRoles(String roleName) {
        Role role = Role.findByName(roleName);
        roles.add(role);
    }

    public void printRole() {
        for (Role role : roles) {
            System.out.println(role.name());
        }
    }
}
