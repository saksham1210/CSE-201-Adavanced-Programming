import org.junit.Test;

public class testVultureException {
    @Test(expected = VulturebiteException.class)
    public void testException() throws VulturebiteException {
        User usr=new User("name",10);
        Vulture v1=new Vulture(2);
        v1.shake(1,usr);
    }
}
