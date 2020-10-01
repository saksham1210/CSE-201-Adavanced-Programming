import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class Company {
    static int unique_id = 0;
    Scanner inp = new Scanner(System.in);
    private static ArrayList<Merchant> merchants;
    private ArrayList<Customer> customers;
    private static double Account_balance;
    private static ArrayList<String> final_category_list;
    private static ArrayList<Item> specific_cat_items;

    public static ArrayList<Item> getSpecific_cat_items() {
        return specific_cat_items;
    }

    public static void addAccount_balance(double account_balance) {
        Account_balance +=account_balance;
    }

    public static ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public static double getAccount_balance() {
        return Account_balance;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<String> getFinal_category_list() {
        return final_category_list;
    }

    public Company() {
        merchants = new ArrayList<>();
        Merchant o1 = new Merchant(1, "John", "Canada", 0);
        Merchant o2 = new Merchant(2, "Sam", "Saudi Arabia", 0);
        Merchant o3 = new Merchant(3, "Dave", "U.S.A", 0);
        Merchant o4 = new Merchant(4, "Mark", "Japan", 0);
        Merchant o5 = new Merchant(5, "Arun", "India", 0);
        merchants.add(o1);
        merchants.add(o2);
        merchants.add(o3);
        merchants.add(o4);
        merchants.add(o5);
        Account_balance=0;
        customers = new ArrayList<>();
        Customer c1 = new Customer(1, "Ishan", "Rohini", 0, 0, 100, 0);
        Customer c2 = new Customer(2, "Arjun", "Delhi Cantt", 0, 0, 100, 0);
        Customer c3 = new Customer(3, "Dhruv", "Rajendra nagar", 0, 0, 100, 0);
        Customer c4 = new Customer(4, "Vrinda", "Preet Vihar", 0, 0, 100, 0);
        Customer c5 = new Customer(5, "Saksham", "Rohini", 0, 0, 100, 0);
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        customers.add(c5);
        final_category_list = new ArrayList<>();
    }

    public void display_merchants() {
        Iterator<Merchant> itr_m = merchants.iterator();
        while (itr_m.hasNext()) {
            Merchant next_u = itr_m.next();
            next_u.display();
        }
    }

    public void display_customers() {
        Iterator<Customer> itr_c = customers.iterator();
        while (itr_c.hasNext()) {
            Customer next_u = itr_c.next();
            next_u.display();
        }
    }

    public void make_category() {
        final_category_list = new ArrayList<>();
        Iterator<Merchant> itr_cat = merchants.iterator();
        while (itr_cat.hasNext()) {
            Merchant next_u = itr_cat.next();
            for (int i = 0; i < next_u.getCategories().size(); i++) {
                String cat = next_u.getCategories().get(i);
                if (final_category_list.contains(cat) == false) {
                    final_category_list.add(cat);
                }
            }
        }
    }

    int n=0;
    public void select()
    {
        System.out.println("Welcome to Mercury");
        System.out.println("1) Enter as Merchant");
        System.out.println("2) Enter as Customer");
        System.out.println("3) See user details");
        System.out.println("4) Company account balance");
        System.out.println("5) Exit");
        n=inp.nextInt();
        while (n != 5)
        {
            if (n == 1)
            {
                System.out.println("Choose merchant");
                display_merchants();
                int um_id = inp.nextInt();
                Merchant mer = merchants.get(um_id - 1);
                int q_m = 0;
                while (true)
                {
                    System.out.println("1) Add item");
                    System.out.println("2) Edit item");
                    System.out.println("3) Search by category");
                    System.out.println("4) Add offer");
                    System.out.println("5) Rewards won");
                    System.out.println("6) Exit");
                    q_m = inp.nextInt();
                    if (q_m == 1) {                                                 //add items
                        System.out.println("Enter Item details");
                        System.out.print("Enter Name: ");
                        String it_name = inp.next();
                        System.out.print("Enter Price: ");
                        int it_price = inp.nextInt();
                        System.out.print("Enter Quantity: ");
                        int it_quan = inp.nextInt();
                        System.out.print("Enter Category: ");
                        String it_cat = inp.next();
                        String it_off = "none";
                        Item it = new Item(unique_id, it_name, it_price, it_quan, it_cat, it_off, mer);
                        unique_id += 1;
                        if ((mer.getMenu().size() + 1 <= 10 + mer.getReward()) == false) {
                            System.out.println("Cannot add more items");
                        }
                        mer.additems(it);
                        it.display();
                    }

                    if (q_m == 2) {                                             //edit price and quantity
                        System.out.println("Choose item by code");
                        mer.display_menu();
                        int id_toEdit = inp.nextInt();
                        int u = mer.choose_object_from_id(id_toEdit);
                        Item it_edit = mer.getMenu().get(u);
                        System.out.print("Enter Price: ");
                        int it_price_edit = inp.nextInt();
                        it_edit.setPrice(it_price_edit);
                        System.out.print("Enter Quantity: ");
                        int it_quan_edit = inp.nextInt();
                        it_edit.setQuantity(it_quan_edit);
                        it_edit.display();
                    }

                    if (q_m == 3) {                                            //searching
                        make_category();                                        //makes category list
                        for (int i = 0; i < final_category_list.size(); i++) {
                            System.out.println((i + 1) + ") " + final_category_list.get(i));
                        }
                        int cat_inp = inp.nextInt();
                        Merchant.search(final_category_list.get(cat_inp - 1));
                    }

                    if (q_m == 4)                                         //add offer
                    {
                        System.out.println("Choose item by code");
                        mer.display_menu();
                        int id_to_edit = inp.nextInt();
                        int f = mer.choose_object_from_id(id_to_edit);
                        Item it_edit1 = mer.getMenu().get(f);
                        System.out.println("Enter Offer: ");
                        System.out.println("1) buy one get one");
                        System.out.println("2) 25% off");
                        int it_offer_edit = inp.nextInt();
                        mer.addOffer(it_offer_edit, it_edit1);

                    }

                    if (q_m == 5) {                                                                   //no. of slots given by reward
                        mer.addReward(mer.getTotal_contribution() / 100);
                        System.out.println("Rewards Won: " + mer.getReward());
                    }
                    if (q_m == 6)                                            //Exit
                    {
                        select();
                    }

                }
            }

            if (n == 2) {
                display_customers();
                int uc_id = inp.nextInt();
                Customer cust = customers.get(uc_id - 1);
                System.out.println("Welcome All");
                while (true) {
                    System.out.println("1) Search item");
                    System.out.println("2) Checkout cart");
                    System.out.println("3) Reward won");
                    System.out.println("4) Print latest offers");
                    System.out.println("5) Exit");
                    int q_c = inp.nextInt();
                    if (q_c == 1)                                       //searching
                    {
                        make_category();                          //makes category
                        specific_cat_items = new ArrayList<>();
                        for (int i = 0; i < final_category_list.size(); i++) {
                            System.out.println((i + 1) + ") " + final_category_list.get(i));
                        }
                        int cat_inp = inp.nextInt();
                        Customer.search(final_category_list.get(cat_inp - 1));

                        System.out.println("Enter Item Code: ");
                        int choose_item = inp.nextInt();
                        Item itm_buy = specific_cat_items.get(0);
                        for (int j = 0; j < specific_cat_items.size(); j++) {
                            if (specific_cat_items.get(j).getId() == choose_item) {
                                itm_buy = specific_cat_items.get(j);
                            }
                        }
                        System.out.print("Enter Item Quantity: ");
                        int itm_qty_need = inp.nextInt();
                        System.out.println("Item chosen");
                        int choo_option = 0;
                        while (choo_option != 3) {
                            System.out.println("1) Buy Item");
                            System.out.println("2) Add to Cart");
                            System.out.println("3) Exit");
                            choo_option = inp.nextInt();
                            if (choo_option == 1)       //buy the item
                            {
                                if (cust.getNumber_of_purchase() % 5 == 0) {
                                    cust.setReward_Account(10);
                                }
                                cust.buy_item(itm_buy, itm_qty_need, 0);
                                choo_option = 3;
                            }
                            if (choo_option == 2)      // add to the cart
                            {
                                if (itm_buy.getQuantity() >= itm_qty_need)
                                {
                                    cust.getCust_cart().addtocart(itm_qty_need, itm_buy);
                                }
                                else {
                                    System.out.println("Not Enough Items in Stock");
                                }
                                choo_option = 3;
                            }
                        }
                    }

                    if (q_c == 2) //checkout
                    {
                        int size=cust.getCust_cart().getCart_items().size();
                        for (int i=size-1;i>=0;i--)
                        {
                            cust.buy_item(cust.getCust_cart().getCart_items().get(i).itm, cust.getCust_cart().getCart_items().get(i).quantity, 1);
                        }
                    }

                    if (q_c == 3) //display reward
                    {
                        System.out.println((cust.getNumber_of_purchase() % 5) * 10);
                    }

                    if (q_c == 4) {
                        //list last 10 orders
                        cust.last_purchases();
                    }
                    if (q_c == 5) {
                        select();
                    }
                }
            }

            if (n == 3) {
                String query = inp.next();
                int id_mc = inp.nextInt();
                if (query.compareTo("M") == 0) {
                    merchants.get(id_mc - 1).display();
                }
                if (query.compareTo("C") == 0) {
                    customers.get(id_mc - 1).display();
                }
            }
            if (n == 4) {
                System.out.println("Account balance: "+getAccount_balance());
                break;
            }
            if (n == 5) {
                System.exit(0);
            }
        }
    }
}

