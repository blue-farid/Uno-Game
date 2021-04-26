public class Card {
    private String color;
    private String number;
    String black = "black";
    String blue = "blue";
    String red = "red";
    String green = "green";
    public Card(String color, String number)
    {
        this.color = color;
        this.number = number;
    }

    public void creatCards()
    {
        for (int i = 0; i < 52; i++) {
            int j = i + 2;
            if (j > 10)
            {
                j -= 10;
                char ch = (char) ((int) 'A' + j);
                String st = "";
                st += ch;
                Config.cards.add(new Card(black,st));
                Config.cards.add(new Card(blue,st));
                Config.cards.add(new Card(red,st));
                Config.cards.add(new Card(green,st));
            }
            Config.cards.add(new Card(black,Integer.toString(j)));
            Config.cards.add(new Card(blue,Integer.toString(j)));
            Config.cards.add(new Card(red,Integer.toString(j)));
            Config.cards.add(new Card(green,Integer.toString(j)));
        }
    }

    public void displayCards()
    {
        int j = 0;
        System.out.print("┌");
        for (int i = 0; i < 10; i++)
            System.out.print("─");
        System.out.println("┐");
        for (int i = 0; i < 4; i++) {
            if (j == 0) {
                System.out.println("│  " + this.number + "    " + this.color +  "  │");
                j++;
            }
            else if (i == 3) {
                System.out.println("│  " + this.color +"    " + this.number +  "  │");
            }
            else
                System.out.println("│          │");
        }
        System.out.print("└");
        for (int i = 0; i < 10; i++)
            System.out.print("─");
        System.out.println("┘");
    }
    public boolean isSpecial() {
        if (this.number.equals("2") || this.number.equals("7") || this.number.equals("8") || this.number.equals("10") || this.number.equals("A") || this.number.equals("B"))
        {
            move = this.number;
            if (this.number.equals("7") && this.color.equals(black) )
            {
                move = "7 white";
            }
            return true;
        }
        else
            return false;
    }
}
