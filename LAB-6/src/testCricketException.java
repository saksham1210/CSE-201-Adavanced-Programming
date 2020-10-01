import org.junit.Test;
public class testCricketException
{
    @Test(expected = CriketbiteException.class)
    public void testException() throws CriketbiteException {
        User usr=new User("name",10);
        Cricket c1=new Cricket(2);
        c1.shake(1,usr);
    }
}
