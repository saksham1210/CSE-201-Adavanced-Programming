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
    private static ArrayList<Sidekick> clone_list;
    private static Sidekick sidekick;
    private static Sidekick min1=new Minion();
    private static Sidekick kni1=new Knight();

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
        clone_list=new ArrayList<>();
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
    public static void Startgame(User currentuser) throws CloneNotSupportedException {
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
    public static void ReStartgame(User currentuser,Node start_loc) throws CloneNotSupportedException                        //for restarting game from same location again
    {
        start_loc=map1.getStart_location();
        int start_loc_num=start_loc.getLocation_number();
        System.out.println("You are at the start location choose a path");
        start_fight(hero1,list_next[start_loc_num].get(0).getMymonster(),list_next[start_loc_num].get(0));
        map1.show_path(start_loc_num);
        System.out.println("Enter -1 to Exit");
        int loc=input.nextInt();
        start_fight(hero1,list_next[start_loc_num].get(loc).getMymonster(),list_next[start_loc_num].get(loc));
        choose_location(list_next[start_loc_num].get(loc).getLocation_number(),0);
    }
    public static void choose_location(int start_loc_num,int itr) throws CloneNotSupportedException {
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
        }
    }
    public static void start_fight(Hero myhero, Monster mymonster,Node initial_Location) throws CloneNotSupportedException {
        addVisited(initial_Location);
        System.out.println("Level:"+initial_Location.getMymonster().getLevel()+" Monster encountered");
        System.out.println("TYPE 'yes' IF YOU WANT TO USE YOUR SIDEKICK ");
        if (hero1.getSidekicks().size()==0)
        {
            System.out.println("Please Enter 'no' You don't have any Sidekicks available Rightnow");
        }
        String op=input.next();
        while (myhero.getHP()>=0 && mymonster.getHP()>=0)
        {
        if (op.compareTo("yes")==0)
        {
            sidekick=hero1.choose_sidekick();

                if (sidekick.getClass()==min1.getClass() && sidekick.isCloned()==false)
                {
                    System.out.println("Do you want to Clone the Minion"+"\n"+"yes/no");
                    String ch=input.next();
                    if (ch.compareTo("yes")==0)
                    {
                        clone_list=sidekick.cloned();             //arraylist of the clones generated
                        System.out.println("Cloning Done");
                    }
                }
                if (sidekick.getHP()<=0 && hero1.getSidekicks().contains(sidekick))                                  //check if sidekick is still alive
                {
                    System.out.println("Sidekick Died");
                    op="no";
                    hero1.getSidekicks().remove(sidekick);
                }
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
                    if (sidekick.getClass()==min1.getClass() && clone_list.size()==3 && sidekick.getHP()>0)
                    {
                        sidekick.attack(mymonster);
                        for (int i=0;i<3;i++) {
                            clone_list.get(i).attack(mymonster);
                            if (i == 0) {
                                mymonster.attack(myhero);
                            }
                            clone_list.get(i).take_damage((int) (myhero.getDamage_taken() * 1.5));
                        }
                        sidekick.take_damage((int) (myhero.getDamage_taken()*1.5));
                    }
                    else if (hero1.getSidekicks().contains(sidekick) && sidekick.getHP()>0)           //check if sidekick is still present
                    {
                        sidekick.attack(mymonster);
                        mymonster.attack(myhero);
                        sidekick.take_damage((int) (myhero.getDamage_taken()*1.5));
                    }
                    System.out.println("your HP:"+myhero.getHP()+" Monster's HP:"+mymonster.getHP());
                }
                if (choose_move==2)
                {
                    if (sidekick.getClass()==kni1.getClass() && mymonster.getLevel()==2 && sidekick.getHP()>0)
                    {
                        mymonster.attack(myhero);
                        myhero.defence_kn(mymonster,myhero.getDamage_taken());
                    }
                    else{
                        mymonster.attack(myhero);
                        myhero.defence(mymonster,myhero.getDamage_taken());
                    }
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
        else{
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
        }
        if (clone_list.size()!=0)
        {
            clone_list.remove(0);
            clone_list.remove(1);
            //clone_list.remove(2);
        }
        if (myhero.getHP()<=0)
        {
            System.out.println("Hero Died");
            System.out.println("Starting the game again");
            User usr1=main.run1.getUser_list().get(main.ch);
            Node visit1=new Node(getVisited().get(0).getLocation_number());
            myhero.setHP(100);
            myhero.setLevel(1);
            myhero.setNumber_of_moves(0);
            ReStartgame(usr1,visit1);                                                //restart game
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
                System.out.println("You've been awarded "+mymonster.getLevel()*20+" Xp");
                myhero.checkLevelup();
                if (op.compareTo("yes")==0)
                {
                    sidekick.setXP(mymonster.getLevel()*2);
                    sidekick.addAttack();
                    sidekick.setHP(100);
                }
                myhero.setNumber_of_moves(0);
                if (myhero.getLevel()==1)
                {
                    myhero.setHP(100);
                }
                if (myhero.getLevel()==2)
                {
                    myhero.setHP(150);
                }
                if (myhero.getLevel()==3)
                {
                    myhero.setHP(200);
                }
                mymonster.setHP(mymonster.getHPoriginal());                          //reviving monster
                choose_location(initial_Location.getLocation_number(),itr);
        }
    }
}
