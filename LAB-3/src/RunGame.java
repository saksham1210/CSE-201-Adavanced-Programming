import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class RunGame
{
    private static Map map1;
    static LinkedList<Node>[] list_next;
    private static ArrayList<User> user_list;
    private  static Hero hero1;
    private static int itr;
    private static ArrayList<Node> visited;

    public static ArrayList<Node> getVisited() {
        return visited;
    }

    public static void addVisited(Node visited_node) {
        visited.add(visited_node);
    }

    static Scanner input=new Scanner(System.in);
    RunGame()
    {
        visited=new ArrayList<>();
        map1=new Map();
        user_list=new ArrayList<>();
        list_next= map1.getG1().getAdjListArray();
        itr=0;
    }

    public static ArrayList<User> getUser_list() {
        return user_list;
    }

    public static void addUser(User currentuser)
    {
        user_list.add(currentuser);
        Scanner input=new Scanner(System.in);
        System.out.println("Welcome "+currentuser.getUsername());
        System.out.println("Please choose a Hero Avatar");
        System.out.println(("1) Warrior"));
        System.out.println(("2) Mage"));
        System.out.println(("3) Thief "));
        System.out.println(("4) Healer"));
        int choose_hero=input.nextInt();
        if (choose_hero==1){hero1=new Warrior();}
        if (choose_hero==3){hero1=new Thief();}
        if (choose_hero==2){hero1=new Mage();}
        if (choose_hero==4){hero1=new Healer();}
        currentuser.addhero(hero1);
        System.out.println("Hero Creation Done");
        System.out.println("Hero type:"+hero1.getClass());
    }
    public static void Startgame(User currentuser)
    {
        Node start_loc=map1.getStart_location();
        int start_loc_num=start_loc.getLocation_number();
        System.out.println("You are at the start location choose a path");
        start_fight(hero1,list_next[start_loc_num].get(0).getMymonster(),list_next[start_loc_num].get(0));
        map1.show_path(start_loc_num);
        System.out.println("Enter -1 to Exit");
        int loc=input.nextInt();
        start_fight(hero1,list_next[start_loc_num].get(loc).getMymonster(),list_next[start_loc_num].get(loc));
        choose_location(list_next[start_loc_num].get(loc).getLocation_number(),0);
    }
    public static void ReStartgame(User currentuser,Node start_loc)        //for restarting game from same location again
    {
        start_loc=map1.getStart_location();
        int start_loc_num=start_loc.getLocation_number();
        System.out.println("You are at the start location choose a path");
        map1.show_path(start_loc_num);
        System.out.println("Enter -1 to Exit");
        int loc=input.nextInt();
        start_fight(hero1,list_next[start_loc_num].get(loc).getMymonster(),list_next[start_loc_num].get(loc));
        choose_location(list_next[start_loc_num].get(loc).getLocation_number(),0);
    }
    public static void choose_location(int start_loc_num,int itr)
    {
        itr+=1;
        map1.show_path(start_loc_num);
        System.out.println("4) Go Back");
        System.out.println("Enter -1 to Exit");
        int loc=0;
        while(loc!=-1)
        {
            if (itr==0)
            {
                map1.show_path(start_loc_num);

                start_fight(hero1,list_next[start_loc_num].get(loc).getMymonster(),list_next[start_loc_num].get(loc));
                System.out.println("Enter -1 to Exit");
            }

            loc=input.nextInt();
            if (loc==4)
            {
                choose_location(getVisited().get(getVisited().size()).getLocation_number(),itr);
            }
            start_fight(hero1,list_next[start_loc_num].get(loc).getMymonster(),list_next[start_loc_num].get(loc));
            choose_location(list_next[start_loc_num].get(loc).getLocation_number(),itr);
            //map1.setPrevious_location(list_next[start_loc_num].get(itr));
        }
    }
    public static void start_fight(Hero myhero, Monster mymonster,Node initial_Location)
    {
        addVisited(initial_Location);
        System.out.println("Level:"+initial_Location.getMymonster().getLevel()+" Monster encountered");
        while (myhero.getHP()>=0 && mymonster.getHP()>=0)
        {
            System.out.println("Choose the next Move");
            System.out.println("1) Attack");
            System.out.println("2) Defence");
            if (myhero.check_specialPower())
            {
                System.out.println("3) Special Power");
            }
            int choose_move=input.nextInt();
            if (choose_move==1)
            {
                System.out.println("You chose to attack");
                myhero.attack(mymonster);
                myhero.addNumber_of_moves();
                mymonster.attack(myhero);
                System.out.println("your HP:"+myhero.getHP()+" Monster's HP:"+mymonster.getHP());
            }
            if (choose_move==2)
            {
                mymonster.attack(myhero);
                myhero.defence(mymonster,myhero.getDamage_taken());
                myhero.addNumber_of_moves();
                System.out.println("your HP:"+myhero.getHP()+" Monster's HP:"+mymonster.getHP());
            }
            if (choose_move==3 && hero1.check_specialPower())
            {
               //special power
                myhero.Special_power(mymonster);
                myhero.addNumber_of_moves();
                mymonster.attack(myhero);
                System.out.println("your HP:"+myhero.getHP()+" Monster's HP:"+mymonster.getHP());
            }

        }
        if (myhero.getHP()<=0)
        {
            System.out.println("Hero Died");
            System.out.println("Starting the game again");
            User usr1=main.run1.getUser_list().get(main.ch);
            Node visit1=new Node(getVisited().get(0).getLocation_number());
            myhero.setHP(100);
            myhero.setLevel(1);
            ReStartgame(usr1,visit1);                                                ///restart game
        }
        else
            {
                if (initial_Location.getLocation_number()==9)
                {
                    System.out.println("LionFang Died" +"\n"+ "---------  YOU WON-------");
                    main.start(main.run1);
                }
                System.out.println("Monster Died");
                myhero.addXP(mymonster.getLevel()*20);
                myhero.checkLevelup();
                mymonster.setHP(mymonster.getHPoriginal());                          //reviving monster
                choose_location(initial_Location.getLocation_number(),itr);
        //map1.setPrevious_location(initial_Location);
        }
    }
}
