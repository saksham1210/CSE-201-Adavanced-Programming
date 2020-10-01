import org.junit.Test;
import java.io.*;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class testSerialize
{
    @Test()
    public void test() throws IOException, GameWinnerException, ClassNotFoundException {
        Game new_game=new Game(100,0,"saksham");
        User old_user=new_game.Serialize_25(0);
        ObjectInputStream in =new ObjectInputStream(new FileInputStream("saksham.txt"));
        User deserialized_user=(User) in.readObject();
        assertTrue(old_user.equals(deserialized_user));
    }
}
