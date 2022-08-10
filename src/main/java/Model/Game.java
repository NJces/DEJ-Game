package Model;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private Stack<StructureCard> cards = new Stack<>();
    private ArrayList<Role> roles = new ArrayList<>();
    private int coins;

    public Game(ArrayList<Player> players) {
        this.players = players;
        initGame();
    }

    /**
     * init roles and structures card and shuffling them, set number of coins(25) & set players source
     */
    private void initGame() {
        Role.initRoles(roles);
        Role.randomShuffling(roles);
        cards = StructureCard.initStructureCardList();
        StructureCard.randomShuffling(cards);
        coins = 25;
        setSource();
    }

    /**
     * set source for all player at start(2 coins & 4 structures card)
     */
    private void setSource() {
        for (Player player : players) {
            ArrayList<StructureCard> cards = new ArrayList<>();
            cards.add(this.cards.pop());
            cards.add(this.cards.pop());
            cards.add(this.cards.pop());
            cards.add(this.cards.pop());
            player.setSource(2, cards);
        }
    }
}
