import java.util.Random;

public class Desk {

    private int direction = 0;  // 0 is clockwise and 1 is anticlockwise.
    private static Random random = new Random(System.currentTimeMillis());
    public void addCardOnDesk()
    {
        Card randomCard = Config.cards.get(random.nextInt(Config.cards.size()));
        while (randomCard.getNumber().equals("B"))
            randomCard = Config.cards.get(random.nextInt(Config.cards.size()));
        Config.cardsOnDesk.add(randomCard);
        Config.cards.remove(randomCard);
    }

    public void addCardOnDesk(Card card)
    {
        Config.cardsOnDesk.add(card);
        Config.cards.remove(card);
    }
    public void displayDesk()
    {
        System.out.println();
        System.out.println("*************** Desk ***************");
        int i = 0;
        for (int j = 0; j < Config.cardsOnDesk.size() - 1; j++ , i++)
        {
            String color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
            System.out.print(color + "┌");
            for (int k = 0; k < 5; k++)
                System.out.print("─");
        }
        String color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
        System.out.print(color + "┌");
        for (int k = 0; k < 10; k++)
            System.out.print("─");
        System.out.println("┐");
        i = 0;
        for (int z = 0; z < 4; z++) {
            for (int j = 0; j < Config.cardsOnDesk.size() - 1; j++, i++) {
                color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
                System.out.print(color + "│  ");
                if (z == 0)
                    System.out.print(Config.cardsOnDesk.get(i).getNumber());
                else if (z == 3)
                    System.out.print(Config.cardsOnDesk.get(i).getColor());
                else
                    System.out.print(" ");
                System.out.print("  ");
                if (Config.cardsOnDesk.get(i).getNumber().equals("10") && (z == 0))
                    System.out.print("\b");
            }
            color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
            System.out.print(color + "│  ");
            if (z == 0)
                System.out.print(Config.cardsOnDesk.get(i).getNumber() + "    " + Config.cardsOnDesk.get(i).getColor() + "  │");
            else if (z == 3)
                System.out.print(Config.cardsOnDesk.get(i).getColor() + "    " + Config.cardsOnDesk.get(i).getNumber() + "  │");
            else
                System.out.print("        │");
            if (Config.cardsOnDesk.get(i).getNumber().equals("10") && (z == 0 || z == 3))
                System.out.print("\b" + " " + "\b\b│");
            System.out.println();
            i = 0;
        }
        i = 0;
        for (int j = 0; j < Config.cardsOnDesk.size() - 1; j++ , i++)
        {
            color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
            System.out.print(color + "└");
            for (int k = 0; k < 5; k++)
                System.out.print("─");
        }
        color = Config.displayColor(Config.cardsOnDesk.get(i).getColor());
        System.out.print(color + "└");
        for (int k = 0; k < 10; k++)
            System.out.print("─");
        System.out.print("┘");
        color = Config.displayColor("reset");
        System.out.println(color);
        System.out.println("");
        System.out.println("************************************");
    }

    public Card getDesk()
    {
        return Config.cardsOnDesk.get(Config.cardsOnDesk.size() - 1);
    }
    public int getDirection() {
        return direction;
    }

    public String directionToString()
    {
        if (direction == 0)
            return "clockwise";
        else
            return "anticlockwise";
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
