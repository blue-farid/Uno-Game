import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Player {
    private String username;
    private boolean turn = false;
    private LinkedList<Card> cards = new LinkedList<>();
    public Player(String username)
    {
        this.username = username;
    }

    public void addFirst7Cards() {
        Random random = new Random(System.currentTimeMillis());
        int remain = 52;
        for (int i = 0; i < 7; i++)
        {
            Card randomCard = Config.cards.get(random.nextInt(remain));
            cards.add(randomCard);
            cards.remove(randomCard);
            remain--;
        }
    }
    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void displayCards()
    {
        for (Card card: cards)
        {
            card.displayCards();
        }
    }
    public void turn() {

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
}
