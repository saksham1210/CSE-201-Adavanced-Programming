import java.util.*;

class Node1
{
    public int quantity;
    public Item itm;
    public double price_bought_for;
    public Node1(int quantity,Item itm,double price_bought_for)
    {
        this.quantity=quantity;
        this.itm=itm;
        this.price_bought_for=price_bought_for;
    }

}

public class Customer implements User
{
    private int User_Id;
    private String Name;
    private String Address;
    private int number_orders_placed;
    private int number_of_purchase;
    private double Account;
    private double Reward_Account;
    Cart cust_cart =new Cart();
    private Stack<Node1> bought_items;

    public void setAccount(double account) {
        Account = account;
    }

    public Cart getCust_cart() {
        return cust_cart;
    }

    public void addNumber_of_purchase() {
        this.number_of_purchase = this.number_of_purchase+1;
    }

    public void addAccount(int account) {
        Account = this.Account+account;
    }

    public void addReward_Account(int reward_Account) {
        Reward_Account = this.Reward_Account+reward_Account;
    }

    @Override
    public int getUser_Id() {
        return User_Id;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    public int getNumber_orders_placed() {
        return number_orders_placed;
    }

    public int getNumber_of_purchase() {
        return number_of_purchase;
    }

    public void setReward_Account(double reward_Account) {
        Reward_Account += reward_Account;
    }

    public double getAccount() {
        return Account;
    }

    public void setNumber_of_purchase(int number_of_purchase) {
        this.number_of_purchase += number_of_purchase;
    }

    public double getReward_Account() {
        return Reward_Account;
    }

    @Override
    public void display()
    {
        //display user id with name
        System.out.println(getUser_Id()+ " "+getName());
    }

    public Customer(int user_Id, String name, String address, int number_orders_placed, int number_of_purchase, int account, int reward_Account)
    {
        User_Id = user_Id;
        Name = name;
        Address = address;
        this.number_orders_placed = number_orders_placed;
        this.number_of_purchase = number_of_purchase;
        Account = account;
        Reward_Account = reward_Account;
        bought_items=new Stack<>();
    }

    public Stack<Node1> getBought_items() {
        return bought_items;
    }

    public void buy_item(Item itm_buy, int itm_qty_need, int func)                                         //func 0==normal buy      func 1==remove from queue
    {
        double price_to_be_paid=(itm_buy.getPrice()*itm_qty_need)+(0.005*(itm_buy.getPrice()*itm_qty_need));
        if (itm_buy.getOffer().compareTo("25% off")==0)
        {
            price_to_be_paid=((itm_buy.getPrice()*itm_qty_need)*0.75)+(0.005*(itm_buy.getPrice()*itm_qty_need));
        }
        if (itm_buy.getOffer().compareTo("Buy one get one free")==0)
        {
            if (itm_buy.getQuantity()!=1)
            {
                itm_qty_need+=itm_qty_need*2;
                itm_buy.setPrice(itm_buy.getPrice()/2);            //price gets halved if buy one get is applicable
            }
        }
        if (itm_buy.getQuantity()>=itm_qty_need )
        {
            if ((this.getAccount()+this.getReward_Account())>=price_to_be_paid)
            {
                if ((this.getAccount()-price_to_be_paid)<0)
                {
                    this.setReward_Account(this.getReward_Account()+(this.getAccount()-price_to_be_paid));
                    this.setAccount(0);
                }
                else {this.setAccount(this.getAccount()-price_to_be_paid);}
                Company.addAccount_balance((0.01*(itm_buy.getPrice()*itm_qty_need)));
                this.setNumber_of_purchase(1);
                itm_buy.getOwner().setTotal_contribution(0.005*price_to_be_paid);
                itm_buy.setQuantity(itm_buy.getQuantity()-itm_qty_need);
                Node1 bought=new Node1(itm_qty_need,itm_buy,price_to_be_paid);
                if (bought_items.size()>=10)
                {
                    bought_items.remove(bought_items.size()-1);
                }
                this.bought_items.push(bought);

                if (func==1)
                {
                    getCust_cart().getCart_items().remove(getCust_cart().getCart_items().size()-1);
                }
                System.out.println("Item Bought");
            }
            else{System.out.println("Not enough balance");}
        }
        else{System.out.println("Items out of stock");}
    }

    public static void search(String category_inp)
    {
        Iterator<String> itr_cat = Company.getFinal_category_list().iterator();
        while (itr_cat.hasNext())
        {
            String next_u=itr_cat.next();
            if (next_u.compareTo(category_inp)==0)
            {
                for (int i=0;i<Company.getMerchants().size();i++)
                {
                    Merchant mr=Company.getMerchants().get(i);
                    ArrayList<Item> list_i=mr.getMenu();
                    for (int j=0;j<list_i.size();j++)
                    {
                        if (list_i.get(j).getCategory().compareTo(category_inp)==0)
                        {
                            list_i.get(j).display();
                            Company.getSpecific_cat_items().add(list_i.get(j));                   //adding items to specific category list of items
                        }
                    }
                }
                break;
            }
        }
    }
    public void last_purchases()
    {
        for (int i=(getBought_items().size()-1);i>=0;i--)
        {
            System.out.println("Bought item :" +this.getBought_items().get(i).itm.getName()+ " quantity: "+this.getBought_items().get(i).quantity+ " for Rs"+this.getBought_items().get(i).price_bought_for+ " from Merchant: "+this.getBought_items().get(i).itm.getOwner().getName());
        }
    }

}
