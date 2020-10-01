/* Junit test suite class */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        testSerialize.class,
        testTrampolineException.class,
        testGameWinnerException.class,
        testCricketException.class,
        testSnakeException.class,
        testVultureException.class

})
public class TestSuite { }
