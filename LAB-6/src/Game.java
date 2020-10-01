import java.io.*;
import java.util.*;

public class Game implements Serializable
{
    private static final long serialVersionUID = 44L;
    private Track track;
    private ArrayList<Tile> mytrack;
    private User user;
    private int number_of_tiles;

    void setTrack(Track track) {
        this.track = track;
    }

    void setMytrack(ArrayList<Tile> mytrack) {
        this.mytrack = mytrack;
    }

    void setUser(User user) {
        this.user = user;
    }

    public void setPosition_pr(int position_pr) {
        this.position_pr = position_pr;
    }

    private Dice dice;
    private int  check_option_25=0;
    private int  check_option_50=0;
    private int  check_option_75=0;
    private int position_pr;

    public int getPosition_pr() {
        return position_pr;
    }

    void setNumber_of_tiles(int number_of_tiles) {
        this.number_of_tiles = number_of_tiles;
    }

    Game(int number_of_tiles,int dobara,String username)
    {
        track=new Track(number_of_tiles,dobara);
        mytrack=track.getTrack();
        this.number_of_tiles=number_of_tiles;
        user=new User(username,number_of_tiles) ;
        System.out.println("Control transferred to the Computer to Roll the die");
        user.setMytrack(track);
        dice=new Dice();
    }
    int roll()
    {
        int num=0;
        while(num!=6)
        {
            num=dice.throw_dice();
            System.out.println(user.getName()+" Rolled "+num);
        }
        System.out.println(user.getName()+" Rolled 6, He's out of the Cage");
        System.out.println("You Get a free roll");
        num=dice.throw_dice();
        System.out.println(user.getName()+" Rolled "+num);
        return num;
    }
    void Catch_game_exception(User user)
    {
        try {
            throw new GameWinnerException("YOU WON");
        } catch (GameWinnerException e) {
            System.out.println(e.getMessage());
            System.out.println("Snake Bites: "+user.getSnake_bites());
            System.out.println("Vulture Bites: "+user.getVulture_bites());
            System.out.println("Cricket Bites: "+user.getCricket_bites());
            System.exit(0);
        }
    }

    //for testing serialization!!!!
    User Serialize_25(int position) throws IOException, ClassNotFoundException {

        Scanner input =new Scanner(System.in);
        System.out.println("Starting the game with "+user.getName()+" at Tile "+(position+1));
        //System.out.println(mytrack.size());
        while (((position+1)!=mytrack.size()) && ((position+1)<mytrack.size()))
        {
            if ((position>(mytrack.size()*0.25) ) && check_option_25==0)
            {
            check_option_25=1;
            //serialize
            user.setPresent_position(position); //update the present position while exiting
            main.serialize(user);
            return user;
            }
            System.out.println("Rolling the dice");
            int th=dice.throw_dice();
            position+=th;
            System.out.println(user.getName()+" Rolled "+th);
            user.setDice_roll();
            System.out.println("Dice Roll Number: "+user.getDice_roll());
            if (position==mytrack.size()-1)
            {
                Catch_game_exception(user);
            }
            if (position>mytrack.size()-1)
            {
                position-=th;
                int po=position;
                th=dice.throw_dice();
                while ((po+=th)!=(mytrack.size()-1))
                {
                    th=dice.throw_dice();
                    System.out.println(user.getName()+" Rolled "+th);
                    user.setDice_roll();
                    System.out.println("Dice Roll Number: "+user.getDice_roll());
                    po+=th;
                    po=position;
                }
            }
            System.out.println(user.getName()+" landed on tile "+(position+1));
            track.move(position,user);
            int moveTo=mytrack.get(position).getAttack();
            System.out.println(mytrack.get(position).getClass()+" Encountered Move "+moveTo+" Places");
            if (!mytrack.get(position).getVisited())
            {
                position=position+moveTo;
            }
            if (moveTo!=0)
            {System.out.println(user.getName()+" landed on tile "+(position+1));}
            if (position<0)
            {
                position=0;
                System.out.println("Reached the starting location");
            }
        }
        return null;
    }


    void Start(int position,int for_game_winner) throws IOException, GameWinnerException {
        Scanner input =new Scanner(System.in);
        System.out.println("Starting the game with "+user.getName()+" at Tile "+(position+1));
        //System.out.println(mytrack.size());
        while (((position+1)!=mytrack.size()) && ((position+1)<mytrack.size()))
        {
            if (for_game_winner==1)
            {
                if ((position>(mytrack.size()*0.25) ) && check_option_25==0)
                {
                    check_option_25=1;
                    System.out.println("DO YOU WANT TO SAVE AND EXIT ?          yes/no");
                    if (input.next().compareTo("yes")==0)
                    {
                        //serialize
                        user.setPresent_position(position); //update the present position while exiting
                        main.serialize(user);
                        System.exit(0);
                    }
                }
                if ((position>(mytrack.size()*0.50)) && check_option_50==0)
                {
                    check_option_50=1;
                    System.out.println("DO YOU WANT TO SAVE AND EXIT ?          yes/no");
                    if (input.next().compareTo("yes")==0)
                    {
                        //serialize
                        user.setPresent_position(position); //update the present position while exiting
                        main.serialize(user);
                        System.exit(0);
                    }
                }
                if ((position>(mytrack.size()*0.75)) && check_option_75==0)
                {
                    check_option_75=1;
                    System.out.println("DO YOU WANT TO SAVE AND EXIT ?          yes/no");
                    if (input.next().compareTo("yes")==0)
                    {
                        //serialize
                        user.setPresent_position(position); //update the present position while exiting
                        main.serialize(user);
                        System.exit(0);
                    }
                }
            }

            System.out.println("Rolling the dice");
            int th=dice.throw_dice();
            position+=th;
            System.out.println(user.getName()+" Rolled "+th);
            user.setDice_roll();
            System.out.println("Dice Roll Number: "+user.getDice_roll());
            if (position==mytrack.size()-1)
            {
                if (for_game_winner==0)
                {
                    throw new GameWinnerException("YOU WON");
                }
                Catch_game_exception(user);
            }
            if (position>mytrack.size()-1)
            {
                position-=th;
                int po=position;
                th=dice.throw_dice();
                while ((po+=th)!=(mytrack.size()-1))
                {
                    th=dice.throw_dice();
                    System.out.println(user.getName()+" Rolled "+th);
                    user.setDice_roll();
                    System.out.println("Dice Roll Number: "+user.getDice_roll());
                    po+=th;
                    po=position;
                }
            }
            System.out.println(user.getName()+" landed on tile "+(position+1));
            track.move(position,user);
            int moveTo=mytrack.get(position).getAttack();
            System.out.println(mytrack.get(position).getClass()+" Encountered Move "+moveTo+" Places");
            if (!mytrack.get(position).getVisited())
            {
                position=position+moveTo;
            }
            if (moveTo!=0)
            {System.out.println(user.getName()+" landed on tile "+(position+1));}
            if (position<0)
            {
                position=0;
                System.out.println("Reached the starting location");
            }
        }
    }
}
