import java.util.ArrayList;

/**
 * The type Config.
 */
public class Config {
    /**
     * The constant players.
     */
    public static ArrayList<Player> players = new ArrayList<>();
    /**
     * The constant cards.
     */
    public static ArrayList<Card> cards = new ArrayList<>();
    /**
     * The constant cardsOnDesk.
     */
    public static ArrayList<Card> cardsOnDesk = new ArrayList<>();
    /**
     * The constant mainDesk.
     */
    public static Desk mainDesk = new Desk();
    /**
     * The constant cardA.
     */
    public static boolean cardA = false;
    /**
     * The constant card7.
     */
    public static boolean card7 = false;
    /**
     * The constant penalty.
     */
    public static int penalty = 0;
    /**
     * The constant numberOfPlayers.
     */
    public static int numberOfPlayers;
    /**
     * The constant packOfCards.
     */
    public static int packOfCards;

    /**
     * Display color string.
     *
     * @param color the color
     * @return the string
     */
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

    /**
     * Print players.
     */
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
