import org.junit.Test;
public class testSnakeException
{
    @Test(expected = SnakebiteException.class)
    public void testException() throws SnakebiteException {
        User usr=new User("name",10);
        Snake s1=new Snake(2);
        s1.shake(1,usr);
    }
}
