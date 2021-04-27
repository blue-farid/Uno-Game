public class SpecialCard extends Card{

    private String move;
    public SpecialCard(String color, String number)
    {
        super(color, number);
        move = number;
//        if (number.equals("7") && color.equals(white))
//            move = number + " " + white;
    }

    public String getMove() {
        return move;
    }
}
