import java.io.Serializable;

public abstract class Tile implements Serializable
{
    private static final long serialVersionUID = 45L;
    public abstract boolean getVisited();
    public abstract void setVisited(boolean visit);
    public abstract int getAttack();
    public abstract void shake(int position,User usr) throws VulturebiteException, SnakebiteException, CriketbiteException, TrampolineException;
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
    public void shake(int position,User usr) throws VulturebiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        usr.setVulture_bites();
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
    public void shake(int position,User usr) throws SnakebiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        usr.setSnake_bites();
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
    public void shake(int position,User usr) throws CriketbiteException {
        System.out.println("Trying to Shake Tile: "+(position+1));
        usr.setCricket_bites();
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
    public void shake(int position,User usr) throws TrampolineException {
        System.out.println("Trying to Shake Tile: "+(position+1));
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
    public void shake(int position,User usr) {
        System.out.println("Trying to Shake Tile: "+(position+1));
        System.out.println("I am a normal Tile");
    }
}