import java.util.*;
public class main
{
    private static Game g1;
    public static void main(String[] Args ) throws GameWinnerException, VulturebiteException, CriketbiteException, SnakebiteException, TrampolineException, Lengthlessthan10 {
        Scanner input =new Scanner (System.in);
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
        g1=new Game(num_tiles);
        int num= g1.roll();
        g1.Start(num);
    }
}
