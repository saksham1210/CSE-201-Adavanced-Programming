import java.util.*;
public class Dice
{
    private Random rand;
    public int throw_dice()
    {
        return rand.nextInt(6)+1;
    }
    Dice()
    {
        rand=new Random();
    }
}
