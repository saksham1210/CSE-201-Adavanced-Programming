import java.io.*;
import java.util.*;


public class main
{
    static void serialize(User u1) throws IOException
    {
        ObjectOutputStream out = null;
        try {
            out =new ObjectOutputStream(new FileOutputStream(u1.getName()+".txt"));
            out.reset();
            out.writeObject(u1);
        }
        finally{
            assert out != null;
            out.close();
        }
    }
     static User deserialize(String username) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in =new ObjectInputStream(new FileInputStream(username+".txt"));
            User g1=(User) in.readObject();
            return g1;
        }
        finally{
            assert in != null;
            in.close();
        }
    }
    private static Game g1;
    public static void main(String[] Args ) throws VulturebiteException, CriketbiteException, SnakebiteException, TrampolineException, IOException, ClassNotFoundException, GameWinnerException {
        Scanner input =new Scanner (System.in);
        System.out.println("DO YOU WANT TO CONTINUE THE GAME FROM WHERE YOU LEFT IT?");
        if (input.next().compareTo("yes")==0)
        {
            System.out.println("Please Enter Your Name: ");
            String username=input.next();
            User u1=deserialize(username);
            g1=new Game(u1.getNumber_of_tiles(),1,username);
            g1.setNumber_of_tiles(u1.getNumber_of_tiles());
            g1.setUser(u1);
            g1.setTrack(u1.getMytrack());
            g1.setMytrack(u1.getMytrack().getTrack());
            g1.Start(u1.getPresent_position(),1);
        }
        else
        {
            boolean val=false;
            int num_tiles=0;
            while (!val) {
                System.out.print("Enter the number of tiles--> ");
                try {
                    num_tiles = input.nextInt();
                    if (num_tiles < 10) {
                        throw new Lengthlessthan10("Please enter a length greater than 10");
                    }
                    val = true;
                } catch (Lengthlessthan10 lengthlessthan10) {
                    System.out.println(lengthlessthan10.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                }
            }
            System.out.println("Please Enter Your Name: ");
            String username=input.next();
            g1=new Game(num_tiles,0,username);
            int num= g1.roll();
            g1.Start(num,1);
        }

    }
}
