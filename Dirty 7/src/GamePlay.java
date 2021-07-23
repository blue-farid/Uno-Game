import java.util.*;

/**
 * Gameplay Class has anything that connect to gameplay. like play a card and actions.
 */
public class GamePlay{

    private static Scanner in = new Scanner(System.in); //a scanner to get input from user.
    private static Random random = new Random(System.currentTimeMillis());// use Random class to make random choice for bots.

    /**
     * gamPlay method has made for human players not the bot.
     *
     * @param player the player
     * @param state  state can be 0 or 1. if state was 0 that means player can get a card from main cards and try again.                                   if state was 1 that means player can not get a card and the turn is over.
     */
    public static void gamePlay(Player player , int state)
    {
        Config.mainDesk.displayDesk();
        int choose = 0;
        String number = Config.mainDesk.getDesk().getNumber();
        String color = Config.mainDesk.getDesk().getColor();
        if (Config.card7)
        {
            boolean has7Card = false;
            System.out.println(player.getUsername() + " has to play a 7 card or takes " + Config.penalty + " cards.");
            for (Card card: player.getCards())
            {
                if (card.getNumber().equals("7"))
                {
                    has7Card = true;
                    break;
                }
            }
            if (has7Card)
            {
                System.out.println("you have 7 card, so choose on of them.");
                player.displayCards();
                choose = in.nextInt();
            } else
            {
                System.out.println("you don't have 7 card, so you have to take " + Config.penalty + " another card.");
                for (int i = 0; i < Config.penalty; i++)
                    player.addCard();
                reset7Card();
                in.nextLine();
                return;
            }
        }
        if(state == 0 && !(Config.card7)) {
            player.displayCards();
            System.out.println("choose a card or enter \'0\' to get a new card.");
            choose = in.nextInt();
            in.nextLine();
        }
        if (choose == 0 || state == 1)
        {
            if (choose == 0)
                player.addCard();
            state = 1;
            player.displayCards();
            System.out.println("choose a card or enter \'0\' to skip your turn.");
            choose = in.nextInt();
            if (choose == 0)
                return;
        }
        choose--;
        if (choose >= player.getCards().size())
        {
            Main.mainPart(player);
            System.out.println("out of index");
            gamePlay(player,state);
            return;
        }
        Card card = player.getCards().get(choose);
        if (Config.card7)
        {
            if(!(card.getNumber().equals(number)))
            {
                Main.mainPart(player);
                System.out.println("wrong card!");
                gamePlay(player,state);
                return;
            }
        }
        if (!(card.getNumber().equals(number) || card.getColor().equals(color) || (card.getNumber().equals("B"))))
        {
            Main.mainPart(player);
            System.out.println("wrong card!");
            gamePlay(player,state);
            return;
        }
        player.playCard(choose);
    }

    /**
     * botGameplay method has made for bots. that makes random decision for a player.
     *
     * @param player the player
     * @return true if bots play a card.
     */
    public static boolean botGamePLay(Player player)
    {
        String number = Config.mainDesk.getDesk().getNumber();
        String color = Config.mainDesk.getDesk().getColor();
        if (Config.card7)
        {
            System.out.println(player.getUsername() + " has to play a 7 card or takes " + Config.penalty + " card.");
            for (Card card: player.getCards())
            {
                if (card.getNumber().equals("7"))
                {
                    player.playCard(card);
                    return true;
                }
                System.out.println(player.getUsername() + " doesn't have 7 card, so he takes " + Config.penalty + " another card.");
                for (int i = 0; i < Config.penalty; i++) {
                    Card card1 = Config.cards.get(random.nextInt(Config.cards.size()));
                    player.getCards().add(card1);
                }
                reset7Card();
                return false;
            }

        }
        for (Card card : player.getCards()) {
            if (card.getNumber().equals(number) || card.getColor().equals(color) || card.getNumber().equals("B")) {
                player.playCard(card);
                return true;
            }
        }
        Card card = Config.cards.get(random.nextInt(Config.cards.size()));
        player.getCards().add(card);
        System.out.println(player.getUsername() + " has got to take a card.");
        if (card.getColor().equals(color) || card.getNumber().equals(number) || card.getNumber().equals("B")) {
            player.playCard(card);
            return true;
        }
        return false;
    }

    /**
     * take a player and checks the player is the winner or not.
     *
     * @param player the player
     * @return true if game is over.
     */
    public static boolean gameIsOver(Player player)
    {
        if (player.getCards().size() == 0)
            return true;
        else
            return false;
    }

    /**
     * specialMoves method has made just for humans player not the bots. that takes a player and a String stand for move.
     *
     * @param player      the player
     * @param specialCard the special card
     */
    public static void specialMoves(Player player , SpecialCard specialCard)
    {
        String move = specialCard.getMove();
        int choose = 0;
        if (move.equals("2"))
        {
            if (!(gameIsOver(player))) {
                System.out.println("choose a player to give him one of your card. (enter a number from 1 to ...)");
                Config.printPlayers();
                choose = in.nextInt();
                in.nextLine();
                Card card = player.getCards().get(random.nextInt(player.getCards().size()));
                player.getCards().remove(card);
                choose--;
                Config.players.get(choose).getCards().add(card);
            }
        } else if (move.equals("8"))
        {
            Main.mainPart(player);
            if (!(gameIsOver(player))) {
                System.out.println("you have another chance (8 card).");
                gamePlay(player, 0);
            }
        } else if (move.equals("10"))
        {
            System.out.println("changing direction.");
            int direction = Config.mainDesk.getDirection();
            if (direction == 0)
            {
                Config.mainDesk.setDirection(1);
            } else
            {
                Config.mainDesk.setDirection(0);
            }
        } else if (move.equals("A"))
        {
            Config.cardA = true;
            int index = indexOfNextPlayer(player);
            System.out.println(player.getUsername() + " muted " + Config.players.get(index) + " (A card)");
            in.nextLine();
        } else if (move.equals("B"))
        {
            System.out.println("choose one of the colors:");
            System.out.println("1- red  2- blue  3- white  4- green");
            choose = in.nextInt();
            in.nextLine();
            if (choose == 1)
            {
                Config.cardsOnDesk.add(new SpecialCard("R","B"));
            } else if (choose == 2)
            {
                Config.cardsOnDesk.add(new SpecialCard("B","B"));
            } else if (choose == 3)
            {
                Config.cardsOnDesk.add(new SpecialCard("W","B"));
            } else if (choose == 4)
            {
                Config.cardsOnDesk.add(new SpecialCard("G","B"));
            } else
            {
                System.out.println("wrong input.");
            }
            Config.cardsOnDesk.remove(Config.cardsOnDesk.size() - 2);
        } else if (move.equals("7"))
        {
            if (specialCard.getColor().equals("W"))
            {
                Config.penalty += 4;
            } else
            {
                Config.penalty += 2;
            }
            Config.card7 = true;
        }
    }

    /**
     * botSpecialMoves has made just for bots. that's just like the specialMoves method with the difference that in this method,
     * choices are random.
     *
     * @param player      the player
     * @param specialCard the special card
     */
    public static void botSpecialMoves(Player player, SpecialCard specialCard)
    {
        String move = specialCard.getMove();
        int choose = 0;
        if (player.getCards().size() <= 0)
        {
            return;
        }
        if (move.equals("2"))
        {
            if (!(gameIsOver(player))) {
                /**
                 * random decision.
                 * @deprecated
                 */
//                choose = random.nextInt(Config.players.size());
//
//                while (player.equals(Config.players.get(choose)))
//                    choose = random.nextInt(Config.players.size());
                /**
                 * smart decision.
                 */

                choose = Config.players.indexOf(Collections.min(Config.playersExceptOne(player), (o1, o2) -> {
                    int player1CardsSize = o1.getCards().size();
                    int player2CardSize = o2.getCards().size();
                    return player1CardsSize-player2CardSize;
                }));

                Card card = player.getCards().get(random.nextInt(player.getCards().size()));
                player.getCards().remove(card);
                Config.players.get(choose).getCards().add(card);
                System.out.println(player.getUsername() + " decide to give a card to " + Config.players.get(choose));
            }
        } else if (move.equals("8"))
        {
            if (!(gameIsOver(player))) {
                System.out.println(player.getUsername() + " has another chance (8 card).");
                botGamePLay(player);
            }
        } else if (move.equals("10"))
        {
            System.out.println("changing direction.");
            int direction = Config.mainDesk.getDirection();
            if (direction == 0)
            {
                Config.mainDesk.setDirection(1);
            } else
            {
                Config.mainDesk.setDirection(0);
            }
        } else if (move.equals("A"))
        {
            Config.cardA = true;
            int index = indexOfNextPlayer(player);
            System.out.println(player.getUsername() + " muted " + Config.players.get(index) + " (A card)");
        } else if (move.equals("B"))
        {
            choose = random.nextInt(4);
            choose++;
            if (choose == 1)
            {
                System.out.println(player.getUsername() + " decide to change color to red");
                Config.cardsOnDesk.add(new SpecialCard("R","B"));
            } else if (choose == 2)
            {
                System.out.println(player.getUsername() + " decide to change color to blue");
                Config.cardsOnDesk.add(new SpecialCard("B","B"));
            } else if (choose == 3)
            {
                System.out.println(player.getUsername() + " decide to change color to white");
                Config.cardsOnDesk.add(new SpecialCard("W","B"));
            } else if (choose == 4)
            {
                System.out.println(player.getUsername() + " decide to change color to green");
                Config.cardsOnDesk.add(new SpecialCard("G","B"));
            } else
            {
                System.out.println("wrong input.");
            }
            Config.cardsOnDesk.remove(Config.cardsOnDesk.size() - 2);
        }
        else if (move.equals("7"))
        {
            System.out.println(player + " plays a 7 card.");
            if (specialCard.getColor().equals("W"))
            {
                Config.penalty += 4;
            } else
            {
                Config.penalty += 2;
            }
            Config.card7 = true;
        }
    }

    /**
     * that prints all of the players score at the end of the game. sorting ascending.
     */
    public static void scoreBoard()
    {
        for (Player player: Config.players)
        {
            int score = 0;
            for (Card card: player.getCards())
            {
                char c = card.getNumber().charAt(0);
                if (c > 47 && c < 58) {
                    if (c == '1')
                        score += 10;
                    else {
                        c -= 48;
                        score += c;
                    }
                }
                else {
                    c -= 65;
                    c += 11;
                    score += c;
                }
            }
            player.setScore(score);
        }

        Collections.sort(Config.players);

        for (Player player: Config.players)
        {
            System.out.println(player + "'s score: " + player.getScore());
        }
    }

    /**
     * that's take a player and with attention to direction of the game, return the next player.
     *
     * @param player the player
     * @return return the index of the next player.
     */
    public static int indexOfNextPlayer(Player player)
    {
        int index = Config.players.indexOf(player);
        if (Config.mainDesk.getDirection() == 0)
            index++;
        else
            index--;
        index %= Config.numberOfPlayers;
        if (index < 0)
        {
            index += Config.numberOfPlayers;
        }
        index %= Config.numberOfPlayers;
        return index;
    }

    /**
     * after 7 cards penalties, resets them.
     */
    public static void reset7Card()
    {
        Config.card7 = false;
        Config.penalty = 0;
    }
}
