
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Player {
    private String username;
    private int score = 0;
    private boolean turn = false;
    private LinkedList<Card> cards = new LinkedList<>();
    public Player(String username)
    {
        this.username = username;
    }

    public void addFirst7Cards() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 7; i++)
        {
            Card randomCard = Config.cards.get(random.nextInt(Config.cards.size()));
            cards.add(randomCard);
            Config.cards.remove(randomCard);
        }
    }
    public void addCard(Card card)
    {
        cards.add(card);
        Config.cards.remove(card);
    }

    public Card removeCard(Card card) {
        cards.remove(card);
        Config.cards.add(card);
        return card;
    }

    public void displayCards() {
        int i = 0;
        for (int j = 0; j < cards.size() - 1; j++ , i++)
        {
            String color = Config.displayColor(cards.get(i).getColor());
            System.out.print(color + "┌");
            for (int k = 0; k < 5; k++)
                System.out.print("─");
        }
        String color = Config.displayColor(cards.get(i).getColor());
        System.out.print(color + "┌");
        for (int k = 0; k < 10; k++)
            System.out.print("─");
        System.out.println("┐");
        i = 0;
        for (int z = 0; z < 4; z++) {
            for (int j = 0; j < cards.size() - 1; j++, i++) {
                color = Config.displayColor(cards.get(i).getColor());
                System.out.print(color + "│  ");
                if (z == 0)
                    System.out.print(cards.get(i).getNumber());
                else if (z == 3)
                    System.out.print(cards.get(i).getColor());
                else
                    System.out.print(" ");
                System.out.print("  ");
                if (cards.get(i).getNumber().equals("10") && (z == 0))
                    System.out.print("\b");
            }
            color = Config.displayColor(cards.get(i).getColor());
            System.out.print(color + "│  ");
            if (z == 0)
                System.out.print(cards.get(i).getNumber() + "    " + cards.get(i).getColor() + "  │");
            else if (z == 3)
                System.out.print(cards.get(i).getColor() + "    " + cards.get(i).getNumber() + "  │");
            else
                System.out.print("        │");
            if (cards.get(i).getNumber().equals("10") && (z == 0 || z == 3))
                System.out.print("\b" + " " + "\b\b│");
            System.out.println();
            i = 0;
        }
        i = 0;
        for (int j = 0; j < cards.size() - 1; j++ , i++)
        {
            color = Config.displayColor(cards.get(i).getColor());
            System.out.print(color + "└");
            for (int k = 0; k < 5; k++)
                System.out.print("─");
        }
        color = Config.displayColor(cards.get(i).getColor());
        System.out.print(color + "└");
        for (int k = 0; k < 10; k++)
            System.out.print("─");
        System.out.print("┘");
        color = Config.displayColor("reset");
        System.out.println(color);
        i =0 ;
        for (int j = 0; j < cards.size(); j++)
        {
            i = j + 1;
            System.out.print("   " + i + "  ");
        }
        System.out.println();
    }

    public void addCard()
    {
        Random random = new Random(System.currentTimeMillis());
        Card randomCard = Config.cards.get(random.nextInt(Config.cards.size()));
        cards.add(randomCard);
        Config.cards.remove(randomCard);
    }
    public void playCard(int index)
    {
        Card card = cards.get(index);
        Config.mainDesk.addCardOnDesk(cards.get(index));
        cards.remove(index);
        if (card.isSpecial())
        {
            SpecialCard specialCard = (SpecialCard) card;
            if (this.equals(Config.players.get(0)))
            {
                GamePlay.specialMoves(this , specialCard.getMove());
            } else
            {
                GamePlay.botSpecialMoves(this , specialCard.getMove());
            }

        }
    }

    public void playCard(Card card)
    {
        Config.mainDesk.addCardOnDesk(card);
        cards.remove(card);
        if (card.isSpecial())
        {
            SpecialCard specialCard = (SpecialCard) card;
            if (this.equals(Config.players.get(0)))
            {
                GamePlay.specialMoves(this , specialCard.getMove());
            } else
            {
                GamePlay.botSpecialMoves(this , specialCard.getMove());
            }

        }
    }
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isTurn() {
        return turn;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
