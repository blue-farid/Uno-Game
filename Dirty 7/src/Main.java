import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Dirty Seven Card Game.
 * This game has been written to run on windows terminal (cmd).
 *
 * @author Farid Masjedi.
 * @version 2.0
 */
public class Main {
    private static Random random = new Random(System.currentTimeMillis()); //a random object to make random decisions.
    private static Scanner in = new Scanner(System.in);// a scanner object to get input from the user.
    private static Card mainCard = new Card("main","main"); // a card object to access to the methods of the Card class.
    private static Player player1; // the human player.
    private static Player winner;// the winner player. it is null until on of the players wins the game.

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        int display = new ProcessBuilder("cmd", "/c", "color", "00").inheritIO().start().waitFor();
        System.out.print(display);
        System.out.println("\b");
        cls();
        System.out.println("\b");
        int mode = 0; // to determine the game mode. in this version we just have single player mode.
        System.out.println("please choose on of the modes bellow:");
        System.out.println("1- Single player");
//        System.out.println("2- Multi player ");
        mode = in.nextInt();
        in.nextLine();
        if (mode == 1)
        {
            System.out.println("How many players are in the game? enter a number between 2 to ...");
            Config.numberOfPlayers = in.nextInt();
            Config.packOfCards = (int) Math.ceil((double) Config.numberOfPlayers / 5);
            for (int i = 0; i < Config.packOfCards; i++)
                mainCard.creatCards();
            in.nextLine();
            System.out.println("please enter a username for yourself:");
            String username = in.nextLine();
            player1 = new Player(username);
            Config.players.add(player1);
            System.out.println("NOTE: after each stop in the game, press enter to continue. like now :))");
            System.out.println();
            for (int i = 0; i < Config.numberOfPlayers - 1; i++) {
                int j = i;
                j += 2;
                Config.players.add(new Player("player" + j));
            }
            for (Player player: Config.players)
            {
                player.addFirst7Cards();
            }
            Config.mainDesk.addFirstCardOnDesk();
            Player playerToStart =  Config.players.get(random.nextInt(Config.numberOfPlayers));
            in.nextLine();
            playerToStart.setTurn(true);
            while (true)
            {
                for (Player player: Config.players) {
                    if (player.isTurn()) {
                        if (player.equals(player1))
                        {
                            mainPart(player);
                            GamePlay.gamePlay(player,0);
                            mainPart(player);
                            Config.mainDesk.displayDesk();
                        }
                        else {
                            mainPart(player);
                            if (GamePlay.botGamePLay(player)) {
                                if (!(Config.mainDesk.getDesk().isSpecial() || Config.mainDesk.getDesk().getNumber().equals("B"))) {
                                    System.out.println(player.getUsername() + " plays a card.");
                                }
                            } else {
                                System.out.println(player.getUsername() + " did not plays a card. (pass)");
                            }
                            Config.mainDesk.displayDesk();
                        }
                        in.nextLine();
                        if (GamePlay.gameIsOver(player))
                        {
                            winner = player;
                            break;
                        }
                        player.setTurn(false);
                        int index = GamePlay.indexOfNextPlayer(player);
                        Player nextPlayer = Config.players.get(index);
                        if (Config.cardA) {
                            index = GamePlay.indexOfNextPlayer(nextPlayer);
                        }
                        Config.players.get(index).setTurn(true);
                        Config.cardA = false;
                        clearDesk();
                        break;
                    } else
                        continue;
                }
                if (winner != null)
                    break;
            }
            System.out.println(winner + " wins!");
            GamePlay.scoreBoard();
            in.nextLine();
        }
    }

    /**
     * clears the screen.
     */
    public static void cls()
    {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }

    /**
     * does not allow desk to have more than 4 element.
     */
    public static void clearDesk()
    {
        if (Config.cardsOnDesk.size() > 4)
        {
            for (int i  = 0; Config.cardsOnDesk.size() - 4 > 0; i++) {
                Config.cards.add(Config.cardsOnDesk.get(0));
                Config.cardsOnDesk.remove(0);
            }
        }
    }

    /**
     * prints players and the number of the cards remain.
     */
    public static void playersCardsRemain()
    {
        int i = 0;
        int j = 0;
        int z = 5;
//        System.out.print("\t\tPlayers and their Remain Cards:\t");
        for (; j < Config.packOfCards; j++) {
            System.out.print("\t ");
            for (; i < z; i++) {
                if (i >= Config.players.size())
                    break;
                Player player = Config.players.get(i);
                System.out.print("{ " + player.getUsername() + " : " + player.getCards().size() + " }    ");
            }
            z += 5;
            System.out.println();
            System.out.println();
            System.out.print("           " + Config.mainDesk.directionToString(0));
        }

    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public static Player getWinner() {
        return winner;
    }

    /**
     * prints the direction of the game and players and the number of the cards remain + the player's turn on the top of the screen.
     *
     * @param player the player
     */
    public static void mainPart(Player player)
    {
        cls(); // clears the screen.
        System.out.print("Direction: " + Config.mainDesk.directionToString());
        playersCardsRemain();
        System.out.println();
        System.out.println();
        System.out.println(player.getUsername() + "'s turn.");
    }
}
