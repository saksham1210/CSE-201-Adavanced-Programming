import java.util.*;

class Node
{
    public int quantity;
    public Item itm;
    public Node(int quantity,Item itm)
    {
        this.quantity=quantity;
        this.itm=itm;
    }

}

public class Cart
{
private ArrayList<Node> cart_items;

    public Cart(){
        cart_items=new ArrayList<Node>();
    }

    public void addtocart(int qty,Item itm1)
    {
        Node n=new Node(qty,itm1);
        cart_items.add(n);
    }

    public ArrayList<Node> getCart_items() {
        return cart_items;
    }
}
