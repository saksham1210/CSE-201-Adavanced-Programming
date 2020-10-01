import java.util.*;
class Node
{
    private Monster mymonster;
    private int Location_number;

    public int getLocation_number() {
        return Location_number;
    }

    public void setLocation_number(int location_number) {
        Location_number = location_number;
    }

    public void setMymonster(Monster mymonster) {
        this.mymonster = mymonster;
    }

    public Monster getMymonster() {
        return mymonster;
    }

    public Node(int location) {
        this.Location_number=location;
    }
}

class Graph
    {
        private int Vertices;
        private LinkedList<Node> adjListArray[];
        Node n0;
        Node n1;
        Node n2;

        public Node getN0() { return n0; }

        public Node getN1() {
            return n1;
        }

        public Node getN2() {
            return n2;
        }

        public LinkedList<Node>[] getAdjListArray() {
            return adjListArray;
        }

        // constructor
        Graph(int V)
        {
            this.Vertices=V;
            adjListArray = new LinkedList[V];
            n0=new Node(0);
            n1=new Node(1);
            n2=new Node(2);
            Node n3=new Node(3);
            Node n4=new Node(4);
            Node n5=new Node(5);
            Node n6=new Node(6);
            Node n7=new Node(7);
            Node n8=new Node(8);
            Node n9=new Node(9);
            for (int i=0;i<V;i++)
            {
                adjListArray[i]=new LinkedList<>();
            }
            adjListArray[0].add(n0);
            adjListArray[1].add(n1);
            adjListArray[2].add(n2);
            adjListArray[3].add(n3);
            adjListArray[4].add(n4);
            adjListArray[5].add(n5);
            adjListArray[6].add(n6);
            adjListArray[7].add(n7);
            adjListArray[8].add(n8);
            adjListArray[9].add(n9);

            for (int i=0;i<3;i++)
            {
                adjListArray[i].add(n3);
                adjListArray[i].add(n4);
                adjListArray[i].add(n5);
            }
            for (int i=3;i<6;i++)
            {
                adjListArray[i].add(n6);
                adjListArray[i].add(n7);
                adjListArray[i].add(n8);
            }
            for (int i=6;i<10;i++)
            {
                adjListArray[i].add(n9);
            }
        }
        public void iter_set_monster()
        {
            for (int i=0;i<this.Vertices;i++)
            {
                LinkedList<Node> list_I = adjListArray[i];
                for (int j=0;j<list_I.size();j++)
                {
                    list_I.get(j).setMymonster(generate_random());
                }
            }
            Monster lion=new LionFang();
            adjListArray[9].get(0).setMymonster(lion);
        }
        public void display_adj_list()
        {
            for (int i=0;i<this.Vertices;i++)
            {
                System.out.println("adj list of vertex: "+i);
                LinkedList<Node> list_I = adjListArray[i];
                for (int j=0;j<list_I.size();j++)
                {
                    System.out.println(list_I.get(j).getLocation_number());
                }
            }
        }

        public Monster generate_random()
        {
            Monster m1 = null;
            Random rand=new Random();
            int choose_monst = rand.nextInt(3);
            if (choose_monst==0){m1=new Goblins();}
            if (choose_monst==1){m1=new Zombies();}
            if (choose_monst==2){m1=new Fiends();}
            return m1;
        }


}
