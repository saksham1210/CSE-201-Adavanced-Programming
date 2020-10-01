import org.junit.Test;

public class testTrampolineException
{
    @Test(expected = TrampolineException.class)
    public void testException() throws TrampolineException {
        User usr=new User("name",10);
        Trampoline t1=new Trampoline(2);
        t1.shake(1,usr);
    }
}
