import javax.lang.model.type.NullType;
import java.util.*;

public class Game
{
    private Track track;
    private ArrayList<Tile> mytrack;
    private String User;
    private Tile w1;
    private Dice dice;
    private int dice_roll=0;
    Game(int number_of_tiles)
    {
        w1=new White(0);
        track=new Track(number_of_tiles);
        mytrack=track.getTrack();
        Scanner input=new Scanner(System.in);
        System.out.println("Please Enter Your Name: ");
        User=input.next();
        System.out.println("Starting the game with "+User+" at Tile 1!");
        System.out.println("Control transferred to the Computer to Roll the die");
        dice=new Dice();
    }
    public int roll()
    {
        int num=0;
        while(num!=6)
        {
            num=dice.throw_dice();
            System.out.println(User+" Rolled "+num);
        }
        System.out.println(User+" Rolled 6, He's out of the Cage");
        System.out.println("You Get a free roll");
        num=dice.throw_dice();
        System.out.println(User+" Rolled "+num);
        return num;
    }

    public void Start(int position) throws GameWinnerException,CriketbiteException, SnakebiteException, VulturebiteException, TrampolineException
    {
        while (((position+1)!=mytrack.size()) && ((position+1)<mytrack.size()))
        {
            try {
                System.out.println("Rolling the dice");
                int th=dice.throw_dice();
                position+=th;
                System.out.println(User+" Rolled "+th);
                dice_roll+=1;
                System.out.println("Dice Roll Number: "+dice_roll);
                if (position==mytrack.size()-1)
                {
                    throw new GameWinnerException("YOU WON");
                }
                if (position>mytrack.size()-1)
                {
                    position-=th;
                    int po=position;
                    th=dice.throw_dice();
                    while ((po+=th)!=(mytrack.size()-1))
                    {
                        th=dice.throw_dice();
                        System.out.println(User+" Rolled "+th);
                        dice_roll+=1;
                        System.out.println("Dice Roll Number: "+dice_roll);
                        po+=th;
                        po=position;
                    }
                }
                System.out.println(User+" landed on tile "+(position+1));
                track.move(position);
                int moveTo=mytrack.get(position).getAttack();
                System.out.println(mytrack.get(position).getClass()+" Encountered Move "+moveTo+" Places");
                if (mytrack.get(position).getVisited()==false)
                {
                    position=position+moveTo;
                }
                if (moveTo!=0)
                {System.out.println(User+" landed on tile "+(position+1));}
                if (position<0)
                {
                    position=0;
                    System.out.println("Reached the starting location");
                }
            } catch (GameWinnerException e) {
                System.out.println(e.getMessage());
                System.out.println("Snake Bites: "+w1.getSnake_bites());
                System.out.println("Vulture Bites: "+w1.getVulture_bites());
                System.out.println("Cricket Bites: "+w1.getCricket_bites());
            }
        }
    }

}
