import java.util.*;

public abstract class Tile
{
    private static int snake_bites=0;
    private static int cricket_bites=0;
    private static int vulture_bites=0;
    private static int trampoline_bites=0;

    public static void setSnake_bites() {
        Tile.snake_bites +=1;
    }

    public static void setCricket_bites() {
        Tile.cricket_bites += 1;
    }

    public static void setVulture_bites() {
        Tile.vulture_bites +=1;
    }

    public static void setTrampoline_bites() {
        Tile.trampoline_bites +=1;
    }

    public static int getCricket_bites() {
        return cricket_bites;
    }

    public static int getVulture_bites() {
        return vulture_bites;
    }

    public static int getTrampoline_bites() {
        return trampoline_bites;
    }

    public static int getSnake_bites() {
        return snake_bites;
    }

    public abstract boolean getVisited();
    public abstract void setVisited(boolean visit);
    public abstract int getAttack();
    public abstract void shake(int position) throws VulturebiteException, SnakebiteException, CriketbiteException, TrampolineException;
}
class Vulture extends Tile
{
    Vulture(int att)
    {
      attack= att;
    }
    private int attack;
    private boolean visited;
    @Override
    public boolean getVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visit) {
        visited=visit;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void shake(int position) throws VulturebiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        setVulture_bites();
        throw new VulturebiteException("Yapping!.... I am Vulture");
    }
}
class Snake extends Tile
{
    private int attack;
    public int getAttack() {
        return attack;
    }
    private boolean visited;
    @Override
    public boolean getVisited() {
        return visited;
    }
    @Override
    public void setVisited(boolean visit) {
        visited=visit;
    }
    Snake(int att)
    {
        attack= att;
    }
    @Override
    public void shake(int position) throws SnakebiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        setSnake_bites();
        throw new SnakebiteException("Hiss.....I am a Snake");
    }

}
class Cricket extends Tile
{
    private int attack;
    public int getAttack() {
        return attack;
    }
    private boolean visited;
    @Override
    public boolean getVisited() {
        return visited;
    }
    @Override
    public void setVisited(boolean visit) {
        visited=visit;
    }
    Cricket(int att)
    {
        attack= att;
    }
    @Override
    public void shake(int position) throws CriketbiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        setCricket_bites();
        throw new CriketbiteException("Chirp!... I am a Cricket");
    }
}
class Trampoline extends Tile
{
    private int attack;

    public int getAttack() {
        return attack;
    }
    private boolean visited;
    @Override
    public boolean getVisited() {
        return visited;
    }
    @Override
    public void setVisited(boolean visit) {
        visited=visit;
    }
    Trampoline(int att)
    {
        attack= att;
    }
    @Override
    public void shake(int position) throws TrampolineException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        setTrampoline_bites();
        throw new TrampolineException("PingPong! I am a Trampoline");
    }
}
class White extends Tile
{
    private int attack;

    public int getAttack() {
        return attack;
    }
    private boolean visited;
    @Override
    public boolean getVisited() {
        return visited;
    }
    @Override
    public void setVisited(boolean visit) {
        visited=visit;
    }
    White(int att)
    {
        attack= att;
    }
    @Override
    public void shake(int position) {
        System.out.println("Trying to Shake Tile: "+(position+1));
        System.out.println("I am a normal Tile");
    }
}