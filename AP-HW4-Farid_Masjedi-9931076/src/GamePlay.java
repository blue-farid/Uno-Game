import java.util.*;
public class GamePlay{

    private static Scanner in = new Scanner(System.in);
    private static Random random = new Random(System.currentTimeMillis());
    public static void gamePlay(Player player , int state)
    {
        Config.mainDesk.displayDesk();
        int choose = 0;
        String number = Config.mainDesk.getDesk().getNumber();
        String color = Config.mainDesk.getDesk().getColor();
        if (Config.card7)
        {
            boolean has7Card = false;
            System.out.println(player.getUsername() + " has to play a 7 card or takes 2 card.");
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
                choose = in.nextInt();
            } else
            {
                System.out.println("you don't have 7 card, so you have got to take 2 another card.");
                Config.card7 = false;
                player.addCard();
                player.addCard();
                return;
            }
        }
        if(state == 0) {
            player.displayCards();
            System.out.println("choose a card or enter \'0\' to get a new card.");
            choose = in.nextInt();
            in.nextLine();
        }
        if (choose == 0)
        {
            if (choose == 0)
                player.addCard();
            player.displayCards();
            System.out.println("choose a card or enter \'0\' to skip your turn.");
            choose = in.nextInt();
            if (choose == 0)
                return;
        }
        choose--;
        Card card = player.getCards().get(choose);
        if (Config.card7)
        {
            if(!(card.getNumber().equals(number)))
            {
                System.out.println("wrong card!");
                gamePlay(player,0);
                return;
            }
        }
        if (!(card.getNumber().equals(number) || card.getColor().equals(color)))
        {
            System.out.println("wrong card!");
            gamePlay(player,0);
            return;
        }
        player.playCard(choose);
    }
    public static boolean botGamePLay(Player player)
    {
        Config.mainDesk.displayDesk();
        String number = Config.mainDesk.getDesk().getNumber();
        String color = Config.mainDesk.getDesk().getColor();
        if (Config.card7)
        {
            System.out.println(player.getUsername() + " has to play a 7 card or takes 2 card.");
            for (Card card: player.getCards())
            {
                if (card.getNumber().equals("7"))
                {
                    player.playCard(card);
                    return true;
                }
                System.out.println(player.getUsername() + " doesn't have 7 card, so he takes 2 another card.");
                Card card1 = Config.cards.get(random.nextInt(Config.cards.size()));
                player.getCards().add(card1);
                card1 = Config.cards.get(random.nextInt(Config.cards.size()));
                player.getCards().add(card1);
                Config.card7 = false;
                return false;
            }

        }
        for (Card card : player.getCards()) {
            if (card.getNumber().equals(number) || card.getColor().equals(color)) {
                player.playCard(card);
                return true;
            }
        }
        Card card = Config.cards.get(random.nextInt(Config.cards.size()));
        player.getCards().add(card);
        System.out.println(player.getUsername() + " has got to take a card.");
        if (card.getColor().equals(color) || card.getNumber().equals(number)) {
            player.playCard(card);
            return true;
        }
        return false;
    }

    public static boolean gameIsOver(Player player)
    {
        if (player.getCards().size() == 0)
            return true;
        else
            return false;
    }

    public static void specialMoves(Player player , String move)
    {
        int choose = 0;
        if (move.equals("2"))
        {
            System.out.println("choose a player to give him one of your card. (enter a number from 1 to ...)");
            Config.printPlayers();
            choose = in.nextInt();
            in.nextLine();
            Card card = player.getCards().get(random.nextInt(player.getCards().size()));
            player.getCards().remove(card);
            choose--;
            Config.players.get(choose).getCards().add(card);
        } else if (move.equals("8"))
        {
            Main.cls();
            System.out.println("you have another chance (8 card).");
            gamePlay(player,0);
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
            System.out.println("choose one of the colors:");
            System.out.println("1- red  2- blue  3- white  4- green");
            choose = in.nextInt();
            in.nextLine();
            if (choose == 1)
            {
                Config.cardsOnDesk.add(new Card("R","B"));
            } else if (choose == 2)
            {
                Config.cardsOnDesk.add(new Card("B","B"));
            } else if (choose == 3)
            {
                Config.cardsOnDesk.add(new Card("W","B"));
            } else if (choose == 4)
            {
                Config.cardsOnDesk.add(new Card("G","B"));
            } else
            {
                System.out.println("wrong input.");
            }
            Config.cardsOnDesk.remove(Config.cardsOnDesk.size() - 2);
        } else if (move.equals("7"))
        {
            Config.card7 = true;
        }
    }

    public static void botSpecialMoves(Player player, String move)
    {
        int choose = 0;
        if (player.getCards().size() <= 0)
        {
            return;
        }
        if (move.equals("2"))
        {
            choose = random.nextInt(Config.players.size());
            while (player.equals(Config.players.get(choose)))
                choose = random.nextInt(Config.players.size());
            Card card = player.getCards().get(random.nextInt(player.getCards().size()));
            player.getCards().remove(card);
            Config.players.get(choose).getCards().add(card);
            System.out.println(player.getUsername() + " decide to give a card to " + Config.players.get(choose));
        } else if (move.equals("8"))
        {
            System.out.println(player.getUsername() + " has another chance (8 card).");
            botGamePLay(player);
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
                Config.cardsOnDesk.add(new Card("R","B"));
            } else if (choose == 2)
            {
                System.out.println(player.getUsername() + " decide to change color to blue");
                Config.cardsOnDesk.add(new Card("B","B"));
            } else if (choose == 3)
            {
                System.out.println(player.getUsername() + " decide to change color to white");
                Config.cardsOnDesk.add(new Card("W","B"));
            } else if (choose == 4)
            {
                System.out.println(player.getUsername() + " decide to change color to green");
                Config.cardsOnDesk.add(new Card("G","B"));
            } else
            {
                System.out.println("wrong input.");
            }
            Config.cardsOnDesk.remove(Config.cardsOnDesk.size() - 2);
        }
        else if (move.equals("7"))
        {
            Config.card7 = true;
        }
    }

    public static void scoreBoard()
    {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Player player: Config.players)
        {
            int score = 0;
            for (Card card: player.getCards())
            {
                char c = card.getNumber().charAt(0);
                if (c > 47 && c < 58)
                    score += c;
                else {
                    c -= 65;
                    c += 11;
                    score += c;
                }
                scores.add(score);
                player.setScore(score);
            }
        }
        Collections.sort(scores);
        for (int i = 0; i < scores.size(); i++)
        {
            for (Player player: Config.players)
            {
                if (scores.get(i) == player.getScore()) {
                    System.out.println(player + "'s score: " + scores.get(i));
                    break;
                }
            }
        }
    }

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
}
