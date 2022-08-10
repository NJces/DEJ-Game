package Model;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private Stack<StructureCard> cards = new Stack<>();
    private ArrayList<Role> roles = new ArrayList<>();
    private int coins;
    private Role curentTurn;

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
        curentTurn = Role.Killer;
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


    public Player nextTurn() {
        curentTurn = Role.nextRole(curentTurn);
        return searchPlayerByRole(curentTurn);
    }

    private Player searchPlayerByRole(Role role) {
        for (Player player : players) {
            if (player.hasRole(role) && !role.isDead()) {
                return player;
            }
        }
        return null;
    }


    //--------roles----------//
    public boolean kill(Role target) {
        target.kill();
        return true;
    }

    public boolean steal(Role target) {
        if (!target.equals(Role.Killer) && !target.isDead()) {
            target.steal();
            return true;
        }
        return false;
    }
}
