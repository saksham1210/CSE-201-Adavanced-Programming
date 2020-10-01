import java.io.Serializable;
import java.util.*;
public class Dice implements Serializable
{
    private static final long serialVersionUID = 42L;
    private Random rand;
    int throw_dice()
    {
        return rand.nextInt(6)+1;
    }
    Dice()
    {
        rand=new Random();
    }
}
