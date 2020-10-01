import java.util.*;

public class User
{
    private final String Username;
    private ArrayList<Hero> myheros;

    public ArrayList<Hero> getMyheros() {
        return myheros;
    }

    public User(String username)
    {
        Username = username;
        myheros=new ArrayList<>();
    }

    public String getUsername() {
        return Username;
    }

    public void addhero(Hero myhero)
    {
        myheros.add(myhero);
    }

}
