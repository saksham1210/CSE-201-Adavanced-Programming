import java.util.*;


public class Merchant implements User
{
    private int User_Id;
    private String Name;
    private String Address;
    private ArrayList<String> categories;
    private int Reward;
    private double Total_contribution;
    private ArrayList<Item> Menu;

    public int getReward() {
        return Reward;
    }

    public void addReward(double reward) {
        Reward +=reward;
    }

    public Merchant(int user_Id, String name, String address, int total_contribution) {
        categories = new ArrayList<>();
        User_Id = user_Id;
        Name = name;
        Address = address;
        Total_contribution = total_contribution;
        Menu=new ArrayList<>();
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

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getTotal_contribution() {
        return Total_contribution;
    }

    public ArrayList<Item> getMenu() {
        return Menu;
    }

    @Override
    public void display()
    {
        //display user id with name
        System.out.println(getUser_Id()+ " "+getName());
    }
    public void additems(Item itm1)
    {
        if (categories.contains(itm1.getCategory())==false)
        {
            categories.add(itm1.getCategory());
        }
        Menu.add(itm1);
    }

    public void setTotal_contribution(double total_contribution) {
        Total_contribution += total_contribution;
    }

    public static void  search(String category_inp)
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
                        }
                    }
                }
                break;
            }
        }
    }
    public void addOffer(int it_offer_edit,Item it_edit1)
    {
        if (it_offer_edit==1)
        {
            it_edit1.setOffer("buy one get one");
        }
        else{it_edit1.setOffer("25% off");}

        it_edit1.display();
    }
    public int choose_object_from_id(int id_toedit)
    {
        int f = 0;
        for (int i = 0; i < getMenu().size(); i++) {
            if (getMenu().get(i).getId() == id_toedit) {
                f = i;
                break;
            }
        }
        return f;
    }
    public void display_menu()
    {
        for (int i=0;i<getMenu().size();i++)
        {
            getMenu().get(i).display();
        }
    }




}
