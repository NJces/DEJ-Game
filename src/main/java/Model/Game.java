package Model;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private Stack<StructureCard> cards = new Stack<>();
    private ArrayList<Role> roles = new ArrayList<>();
    private int coins;
    private Role curentTurn;
    private Player curentPlayer;

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
        setCrown();
        curentTurn =null;
        curentPlayer = players.get(0);
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

    /**
     * set crown for older player at the start
     */
    private void setCrown() {
        Player olderPlayer = findOlderPlayer();
        olderPlayer.setCrown();
        //set older player at first
        int olderPlayerIndex = players.indexOf(olderPlayer);
        players.set(olderPlayerIndex, players.get(0));
        players.set(0, olderPlayer);
    }

    /**
     * set crown in next turn for player that has King card role
     */
    public void setCrownInGame() {
        for (Player player : players) {
            if (player.getCrown()) {
                int kingIndex = players.indexOf(player);
                players.set(kingIndex, players.get(0));
                players.set(0, player);
            }
        }
    }

    /**
     * check if a player has King card role
     * @return true (if a player is King)
     */
    public boolean hasKingCardRole() {
        for (Player player : players) {
            if (player.hasRole(Role.King)) {
                return true;
            }
        }
        return false;
    }

    private Player findOlderPlayer() {
        int maxAge = 0;
        for (Player player : players) {
            if (player.getAge() >= maxAge) {
                maxAge = player.getAge();
            }
        }

        for (Player player : players) {
            if (player.getAge() == maxAge) {
                return player;
            }
        }

        return null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurentPlayer() {
        return curentPlayer;
    }

    public Role getCurentTurn() {
        return curentTurn;
    }

    /**
     * Specify next turn by roles number
     * @return Player (that has curent role)
     */
    public Player nextTurn() {
        Player player = null;
        while (player == null) {
            if (curentTurn == null) {
                curentTurn = Role.Killer;
            } else {
                curentTurn = Role.nextRole(curentTurn);
            }
            player = searchPlayerByRole(curentTurn);
        }
        return player;
    }

    /**
     * find player by that has the role
     * @param role
     * @return player (if player by this role exist) else return null
     */
    private Player searchPlayerByRole(Role role) {
        for (Player player : players) {
            if (player.hasRole(role) && !role.isDead()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Specify the next turn based on the order of the players in list
     * @return curentTurn
     */
    public Player nextPlayer() {
        int curentPlayerIndex = players.indexOf(curentPlayer);
        if (curentPlayerIndex < players.size()-1) {
            curentPlayer = players.get(curentPlayerIndex + 1);
        }
        else {
            curentPlayer = players.get(0);
        }
        return curentPlayer;
    }

    /**
     * when a game loop is finished all roles must be taken from the player and curent player equal to King player
     */
    public void finishedAGameLoop() {
        for (Player player : players) {
            if (player.getCrown()) {
                curentPlayer = player;
            }
            player.finishedAGameLoop();
        }
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

    public boolean magic(Role target) {
        target.magic();
        return true;
    }

    public boolean coronation(Player newKing) {
        for (Player player : players) {
            if (player.getCrown()) {
                player.lossCrown();
            }
        }
        newKing.setCrown();
        return true;
    }

    public boolean trader(Player trader) {
        coins --;
        trader.getCoins(1);
        return true;
    }

    public boolean commander(Player commander, Player target, StructureCard targetCard) {
        if (!target.hasRole(Role.Doctor) && commander.getNumOfCoins() >= targetCard.getCost()-1) {
            target.giveCoins(targetCard.getCost()-1);
            target.destructCard(targetCard);
            return true;
        }
        return false;
    }
    //--------roles----------//


}
