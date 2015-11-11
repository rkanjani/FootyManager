package oceansfive.footymanager;

/**
 * Created by Samsore on 2015-11-11.
 */
public class Player {

    private String name;
    private Character position;  // Only Possible positions: D, M, F, G
    private int jerseyNumber; //2 Digit numbers only

    public Player(String name, char position, int jerseyNumber)
    {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public char getPosition()
    {
        return position;
    }

    public void setPosition(Character position)
    {
        position = Character.toUpperCase(position);
        if (position != 'F' && position != 'D' && position != 'M' && position != 'G' )
        {
            System.out.println();
        }
        else
        {
            this.position = position;
        }

    }

    public boolean isGoalie()
    {
        if (this.position == 'G')
            return true;
        else
            return false;
    }



}
