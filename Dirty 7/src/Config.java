import java.util.ArrayList;

public class Config {
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Card> cards = new ArrayList<>();
    public static ArrayList<Card> cardsOnDesk = new ArrayList<>();
    public static Desk mainDesk = new Desk();
    public static boolean cardA = false;
    public static boolean card7 = false;
    public static int penalty = 0;
    public static int numberOfPlayers;
    public static int packOfCards;
    public static String displayColor(String color) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";
        if (color.equals("W"))
        {
            return ANSI_WHITE;
        } else if (color.equals("B"))
        {
            return ANSI_BLUE;
        } else if (color.equals("R"))
        {
            return ANSI_RED;
        } else if (color.equals("G"))
        {
            return ANSI_GREEN;
        }
        else
            return ANSI_RESET;
    }

    public static void printPlayers()
    {
        System.out.print("{");
        for (Player player: players)
        {
            System.out.print(player + " , ");
        }
        System.out.println("\b\b\b}");
    }
}
