import java.util.*;

public class Map
{
    private Node start_location;        //randomly selected start_location
    private Graph g1;
    public Graph getG1() {
        return g1;
    }

    public Node getStart_location() {
        return start_location;
    }

    Map()
    {
        g1=new Graph(10);                   //makes the graph
        Random rand=new Random();
        int choose = rand.nextInt(3);
        if (choose==0){start_location=g1.getN0();}
        if (choose==1){start_location=g1.getN1();}
        if (choose==2){start_location=g1.getN2();}
        g1.iter_set_monster();                        //sets all the monsters
    }
    public void show_path(int start_loc_num)
    {
        System.out.println("You've reached location: "+start_loc_num);
        LinkedList<Node> list_next= this.g1.getAdjListArray()[start_loc_num];
        int i=0;
        for (i=1;i<list_next.size();i++)
        {
            int min_level=1;
            int min_level_j=0;
            for (int j=0;j<list_next.size();j++)
            {
                if (list_next.get(j).getMymonster().getLevel()<=min_level)
                {
                    min_level=list_next.get(j).getMymonster().getLevel();
                    min_level_j=j;
                }
            }
            if (i==min_level_j)
            {
                System.out.println(i+") "+"Go to Location: "+ list_next.get(i).getLocation_number()+"     HINT: level of monster here-->"+list_next.get(i).getMymonster().getLevel());
            }
            else{System.out.println(i+") "+"Go to Location: "+ list_next.get(i).getLocation_number());}

        }
    }
}
