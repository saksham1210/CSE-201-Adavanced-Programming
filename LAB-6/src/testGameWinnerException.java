import org.junit.Test;

import java.io.IOException;

public class testGameWinnerException
{
    @Test(expected = GameWinnerException.class)
    public void testException() throws GameWinnerException, IOException, CriketbiteException, SnakebiteException, VulturebiteException, TrampolineException {
        Game new_game=new Game(100,0,"saksham");
        new_game.Start(0,0);
    }
}
