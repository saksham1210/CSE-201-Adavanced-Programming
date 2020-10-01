import java.util.ArrayList;

public abstract class Sidekick{
    private int HP=100;
    private int XP;
    private double attack;
    private int extraXP_taken;
    @Override
    public boolean equals (Object o1) {
        if(o1 != null ) {
            Sidekick o = (Sidekick) o1;
            return  ( XP == o.XP);
        }
        else {
            return false;
        }
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    private ArrayList<Sidekick> clones;

    public ArrayList<Sidekick> getClones() {
        return clones;
    }

    public int getExtraXP_taken() {
        return extraXP_taken;
    }

    public void setExtraXP_taken(int extraXP_taken) {
        this.extraXP_taken = extraXP_taken;
        for (int i=0;i<extraXP_taken;i++)
        {
            this.attack+=0.5;
        }

    }

    public int getHP() {
        return HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP += XP;
    }

    public double getAttack() {
        return attack;
    }

    public void addAttack() {

        this.attack +=(XP/5);
    }
    public abstract void attack(Monster mymonster);
    public abstract boolean isCloned();
    public abstract ArrayList<Sidekick> cloned() throws CloneNotSupportedException;
    public void take_damage(int damage)           //gets attacked
    {
        this.HP-=damage;
        System.out.println("Sidekicks HP: "+ this.HP);
    }
    public Sidekick()
    {
        this.HP=100;
        this.XP=0;
        clones=new ArrayList<>();
        this.extraXP_taken=0;
        this.attack=0;
    }

}

class Minion extends Sidekick implements Cloneable
{
    private boolean cloned=false;
    @Override
    public boolean isCloned() {
        return cloned;
    }

    @Override
    public ArrayList<Sidekick> cloned() throws CloneNotSupportedException {
        cloned=true;
        for (int i=0;i<3;i++)
        {
            Sidekick Sidekick1=this.Clone();
            this.getClones().add(Sidekick1);
        }
        return getClones();
    }

    public Sidekick Clone() throws CloneNotSupportedException {
        cloned=true;
        return (Sidekick) super.clone();
    }

    @Override
    public void attack(Monster mymonster) {
        mymonster.damageHP(1+getAttack());
        System.out.println("Minion attcked with a damage of "+(1+getAttack())+" HP");
    }
}
class Knight extends Sidekick
{
private boolean cloned=false;
    @Override
    public void attack(Monster mymonster) {
        mymonster.damageHP(2+getAttack());
        System.out.println("Knight attcked with a damage of "+(2+getAttack())+"  HP");
    }
    @Override
    public boolean isCloned()
    {
        return cloned;
    }

    @Override
    public ArrayList<Sidekick> cloned() throws CloneNotSupportedException {
        return null;
    }
}


