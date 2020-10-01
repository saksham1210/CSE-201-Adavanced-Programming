import java.util.*;

public class main
{
    public static int ch=0;
    public static RunGame run1;
    public static void start(RunGame run1)
    {
        System.out.println("Welcome to Archlegends");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
        Scanner input=new Scanner(System.in);
        while (true) {
            int inp = input.nextInt();
            if (inp == 1) {
                System.out.println("Enter Username");
                String username = input.next();
                User usr = new User(username);
                run1.addUser(usr);
                System.out.println("User Added");
            }
            if (inp == 2) {
                System.out.println("Enter a Username");
                String choose = input.next();
                for (int i = 0; i < run1.getUser_list().size(); i++) {
                    if (run1.getUser_list().get(i).getUsername().compareTo(choose) == 0) {
                        System.out.println("Found user Logging in");
                        break;
                    }
                    ch = i;
                }
                User usr1 = run1.getUser_list().get(ch);
                run1.Startgame(usr1);
            }
            if (inp == 3) {
                System.exit(0);
            }
        }
    }
    public static void  main(String[] args)
    {
        run1=new RunGame();
        start(run1);
    }

}
