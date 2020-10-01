import java.util.Random;

public class Monster
{
private int HP;
private  int HPoriginal;

    public void setHPoriginal(int HPoriginal) {
        this.HPoriginal = HPoriginal;
    }

    public int getHPoriginal() {
        return HPoriginal;
    }

    private int Level;

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void damageHP( int damage)
    {
        this.HP-=damage;                            //taking damage
    }
    public void damageHP_hero(Hero myhero, int damage)
    {
        myhero.reduceHP_hero(damage);
    }         //giving damage
    public void attack(Hero myhero)
    {
        Random rand=new Random();
        double value;
        int dg;
        do
        {
            value=Math.abs(rand.nextGaussian());
            dg= (int) ( value*HP*0.25);
        }while((value>1));

        System.out.println("Monster Attacked : "+dg+": HP reduced");
        this.damageHP_hero(myhero,dg);                   //giving damage
    }
}

class Goblins extends Monster
{
    public Goblins()
    {
        this.setLevel(1);
        this.setHP(100);
        this.setHPoriginal(100);
    }
}
class Zombies extends Monster
{
    public Zombies()
    {
        this.setLevel(2);
        this.setHP(150);
        this.setHPoriginal(150);
    }
}
class Fiends extends Monster
{
    public Fiends()
    {
        this.setLevel(3);
        this.setHP(200);
        this.setHPoriginal(200);
    }
}
class LionFang extends Monster
{
    public LionFang()
    {
        this.setLevel(4);
        this.setHP(250);
        this.setHPoriginal(250);
    }
    @Override
    public void attack(Hero myhero)
    {
        Random rand=new Random();
        int half=rand.nextInt(10);
        int dg= (int) (rand.nextGaussian()+myhero.getHP()/8);
        if (half==0)
        {
            this.damageHP_hero(myhero,myhero.getHP()/2);
        }
        else
        {
            damageHP_hero(myhero,dg);
        }
    }

}
