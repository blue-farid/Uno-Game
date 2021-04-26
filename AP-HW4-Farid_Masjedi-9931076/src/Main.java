import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Random random = new Random(System.currentTimeMillis());
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int mode = 0;
        System.out.println("please choose on of the modes bellow:");
        System.out.println("1- Single player");
        System.out.println("2- Multi player ");
        mode = in.nextInt();
        if (mode == 1)
        {

            System.out.println("How many players are in the game? enter a number between 2 - 5");
            int numberOfPlayers = in.nextInt();
            System.out.println("please enter a username for yourself:");
            String username = in.nextLine();
            Player player1 = new Player(username);
            Config.players.add(player1);
            for (int i = 0; i < numberOfPlayers; i++) {
                int j = i;
                j += 2;
                Config.players.add(new Player("player" + j));
            }
            for (Player player: Config.players)
            {
                player.addFirst7Cards();
            }
            Config.players.get(random.nextInt(numberOfPlayers)).setTurn(true);
            while (true)
            {

            }
        }
    }
}
