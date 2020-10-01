public class Item
{
    private int Id;
    private String Name;
    private int Price;
    private String Category;
    private String Offer;
    private Merchant owner;
    private int Quantity;
    static int id=0;
    public Item(int id, String name, int price,int quantity, String category, String offer, Merchant owner) {
        Id = id;
        Name = name;
        Price = price;
        Category = category;
        Offer = offer;
        Quantity = quantity;
        this.owner = owner;
    }

    public void setOffer(String offer) {
        Offer = offer;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void display()
    {
        System.out.println(getId()+" "+getName()+" "+getPrice()+" "+getQuantity()+" "+getCategory()+" "+getOffer());
    }


    public int getId() {
        return Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public String getCategory() {
        return Category;
    }

    public String getOffer() {
        return Offer;
    }

    public Merchant getOwner() {
        return owner;
    }
}
