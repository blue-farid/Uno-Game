import java.io.IOException;
import java.util.Objects;

public class Card {
    private String color;
    private String number;
    protected final String white = "W";
    protected final String blue = "B";
    protected final String red = "R";
    protected final String green = "G";
    public Card(String color, String number)
    {
        this.color = color;
        this.number = number;
    }
    public void creatCards()
    {
        for (int i = 0; i < 13; i++) {
            int j = i + 2;
            if (j > 10)
            {
                j -= 11;
                char ch = (char) ((int) 'A' + j);
                String st = "";
                st += ch;
                if (st.equals("A") || st.equals("B"))
                {
                    Config.cards.add(new SpecialCard(white,st));
                    Config.cards.add(new SpecialCard(blue,st));
                    Config.cards.add(new SpecialCard(red,st));
                    Config.cards.add(new SpecialCard(green,st));
                    continue;
                }
                Config.cards.add(new NormalCard(white,st));
                Config.cards.add(new NormalCard(blue,st));
                Config.cards.add(new NormalCard(red,st));
                Config.cards.add(new NormalCard(green,st));
                continue;
            }
            if ( j == 2 || j == 7 || j == 8 || j == 10)
            {
                Config.cards.add(new SpecialCard(white,Integer.toString(j)));
                Config.cards.add(new SpecialCard(blue,Integer.toString(j)));
                Config.cards.add(new SpecialCard(red,Integer.toString(j)));
                Config.cards.add(new SpecialCard(green,Integer.toString(j)));
                continue;
            }
            Config.cards.add(new NormalCard(white,Integer.toString(j)));
            Config.cards.add(new NormalCard(blue,Integer.toString(j)));
            Config.cards.add(new NormalCard(red,Integer.toString(j)));
            Config.cards.add(new NormalCard(green,Integer.toString(j)));
        }
    }

    public void displayCard() {
        String color = Config.displayColor(this.color);
        int j = 0;
        System.out.print(color + "┌");
        for (int i = 0; i < 10; i++)
            System.out.print("─");
        System.out.println("┐");
        for (int i = 0; i < 4; i++) {
            if (j == 0) {
                System.out.print("│  " + this.number + "    " + this.color +  "  │");
                if (this.number.equals("10"))
                    System.out.print("\b\b│");
                System.out.println();
                j++;
            }
            else if (i == 3) {
                System.out.print("│  " + this.color +"    " + this.number +  "  │");
                if (this.number.equals("10"))
                    System.out.print("\b\b│");
                System.out.println();
            }
            else
                System.out.println("│          │");
        }
        System.out.print("└");
        for (int i = 0; i < 10; i++)
            System.out.print("─");
        System.out.println("┘");
        System.out.println(Config.displayColor("reset"));
    }
    public boolean isSpecial()
    {
        if (this instanceof SpecialCard)
        {
            return true;
        }
        else
            return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(color, card.color) && Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number);
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }
}
