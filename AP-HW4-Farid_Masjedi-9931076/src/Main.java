import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Random random = new Random(System.currentTimeMillis());
    private static Scanner in = new Scanner(System.in);
    private static Card mainCard = new Card("main","main");
    private static Player player1;
    private static Player winner;
    public static void main(String[] args) throws IOException, InterruptedException {
        int display = new ProcessBuilder("cmd", "/c", "color", "00").inheritIO().start().waitFor();
        System.out.print(display);
        System.out.println("\b");
        cls();
        System.out.println("\b");
        int mode = 0;
        mainCard.creatCards();
        System.out.println("please choose on of the modes bellow:");
        System.out.println("1- Single player");
        System.out.println("2- Multi player ");
        mode = in.nextInt();
        in.nextLine();
        if (mode == 1)
        {
            System.out.println("How many players are in the game? enter a number between 2 - 5");
            Config.numberOfPlayers = in.nextInt();
            in.nextLine();
            System.out.println("please enter a username for yourself:");
            String username = in.nextLine();
            player1 = new Player(username);
            Config.players.add(player1);
            for (int i = 0; i < Config.numberOfPlayers - 1; i++) {
                int j = i;
                j += 2;
                Config.players.add(new Player("player" + j));
            }
            for (Player player: Config.players)
            {
                player.addFirst7Cards();
            }
            Config.mainDesk.addCardOnDesk();
            Player playerToStart =  Config.players.get(random.nextInt(Config.numberOfPlayers));
            playerToStart.setTurn(true);
            while (true)
            {
                for (Player player: Config.players) {
                    if (player.isTurn()) {
                        System.out.println(player.getUsername() + "'s turn.");
                        System.out.println("cards remain: " + player.getCards().size());
                        in.nextLine();
                        if (player.equals(player1))
                        {
                            GamePlay.gamePlay(player,0);
                        }
                        else if (GamePlay.botGamePLay(player)) {
                            System.out.println(player.getUsername() + " plays a card.");
                        } else
                            System.out.println(player.getUsername() + " did not plays a card. (pass)");
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
            GamePlay.scoreBoard();
            in.nextLine();
        }
    }
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

    public static void clearDesk()
    {
        if (Config.cardsOnDesk.size() > 5)
        {
            Config.cards.add(Config.cardsOnDesk.get(0));
            Config.cardsOnDesk.remove(0);
        }
    }
}
