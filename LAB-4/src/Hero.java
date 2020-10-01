import java.util.*;

public abstract class Hero
{
    static class CustomComparator implements Comparator<Sidekick> {

        @Override
        public int compare(Sidekick o1, Sidekick o2)
        {
            if (o1.getXP()==o2.getXP())
            {
                return 0;
            }
            if (o1.getXP()>o2.getXP())
            {
                return 1;
            }

            if (o1.getXP()>o2.getXP())
            {
                return -1;
            }
            return 0;
        }
    }
    private double HP;
    private int XP;

    public void setXP(int XP) {
        this.XP = XP;
    }

    private int Level;
    private int attack;
    private int defence;
    private int number_of_moves;

    public void setNumber_of_moves(int number_of_moves) {
        this.number_of_moves = number_of_moves;
    }

    private boolean used_special=true;
    private double damage_taken=0;
    private ArrayList<Sidekick> sidekicks;
    Scanner input=new Scanner(System.in);
    public ArrayList<Sidekick> getSidekicks() {
        return sidekicks;
    }
    public Sidekick max_Sidekick()               //returns the sidekick with max XP
    {
        Collections.sort(this.sidekicks,new CustomComparator());
        Sidekick max=sidekicks.get(0);
        return max;
    }
    public Sidekick choose_sidekick()
    {
        return max_Sidekick();
    }
    public void buy_sidekick()
    {
        System.out.println("Your Curren Xp: "+this.getXP());
        System.out.println("1) Buy a Minion");
        System.out.println("2) Buy a Knight ");
        int choose=input.nextInt();
        if (choose==1)
        {
            if (this.XP<5)
            {
                System.out.println("You do not have enough XP to buy a minion");
            }
            else{
                System.out.println("XP to spend: ");
                int xp_given=input.nextInt();
                Sidekick mini1=new Minion();
                this.sidekicks.add(mini1);
                mini1.setExtraXP_taken(xp_given-5);
                this.setXP(this.getXP()-xp_given);
            }
        }
        else{
            if (this.XP<8)
            {
                System.out.println("You do not have enough XP to buy a Knight");
            }
            else{
                System.out.println("XP to spend: ");
                int xp_given=input.nextInt();
                Sidekick knight1=new Knight();
                this.sidekicks.add(knight1);
                knight1.setExtraXP_taken(xp_given-8);
                this.setXP(this.getXP()-xp_given);
            }
        }
    }

    public double getDamage_taken() {
        return damage_taken;
    }

    public void setDamage_taken(double damage_taken) {
        this.damage_taken = damage_taken;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public int getNumber_of_moves() {
        return number_of_moves;
    }

    public void addNumber_of_moves() {
        this.number_of_moves +=1;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level =level;
        this.attack+=1;
        this.defence+=1;
    }

    public double getHP() {
        return HP;
    }

    public void addHP(double HP) {
        this.HP += HP;
    }

    public int getXP() {
        return XP;
    }

    public void addXP(int XP) {
        this.XP += XP;
        System.out.println("Your XP increased by: "+XP);
    }

    public boolean check_specialPower()
    {
        if ((number_of_moves%3==0 || used_special==false) && number_of_moves!=0 )
        {
            return true;
        }
        return false;
    }

    public void checkLevelup()
    {

        if (XP<40 && XP>=20)
        {
            System.out.println("Choose a option:");
            System.out.println("1) Upgrade your Level");
            System.out.println("2) Buy a Sidekick");
            int option=input.nextInt();
            if (option==2)
            {
                buy_sidekick();
            }
            else{
                this.setLevel(1);
                this.HP=150;
                System.out.println("You Upgraded got your level upgraded to:"+this.getLevel());}

        }
        if (XP<60 && XP>=40)
        {
            System.out.println("Choose a option:");
            System.out.println("1) Upgrade your Level");
            System.out.println("2) Buy a Sidekick");
            int option=input.nextInt();
            if (option==2)
            {
                buy_sidekick();
            }
            else {
                this.setLevel(2);
                this.HP = 200;
                System.out.println("You Upgraded got your level upgraded to:"+this.getLevel());
            }

        }
        if (XP>=60)
        {
            System.out.println("Choose a option:");
            System.out.println("1) Upgrade your Level");
            System.out.println("2) Buy a Sidekick");
            int option=input.nextInt();
            if (option==2)
            {
                buy_sidekick();
            }
            else{
                this.setLevel(3);
                this.HP = 250;
                System.out.println("You Upgraded got your level upgraded to:"+this.getLevel());
            }

        }

    }

    public Hero() {
        this.HP = 100;
        this.XP = 0;
        this.Level=0;
        this.attack=0;
        this.defence=0;
        sidekicks=new ArrayList<>();

    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public boolean isUsed_special() {
        return used_special;
    }

    public void setUsed_special(boolean used_special) {
        this.used_special = used_special;
    }

    public abstract void attack(Monster mymons);
    public abstract void defence(Monster mymonster,double last_attack);
    public abstract void defence_kn(Monster mymonster,double last_attack);
    public abstract void reduceHP_hero(double damage_gain);
    public abstract void Special_power(Monster mymonster);
}

class Warrior extends Hero
{
    private int special_power=-1;
    @Override
    public void attack(Monster mymonster)
    {
        if (special_power!=-1 && special_power<=3)
        {
            mymonster.damageHP(15+getAttack());
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
            System.out.println("You chose to Attack with a value of: "+(getAttack()+15));
        }
        else{
            mymonster.damageHP(10+getAttack());
            System.out.println("You chose to Attack with a value of: "+(getAttack()+10));
        }


    }
    @Override
    public void defence(Monster mymonster,double last_attack)
    {

        if (special_power!=-1 && special_power<=3)
        {
            if (8+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(8+getDefence());}

            special_power+=1;
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
        }
        else{
            if (3+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(3+getDefence());}
        }
    }
    @Override
    public void defence_kn(Monster mymonster,double last_attack)
    {

        if (special_power!=-1 && special_power<=3)
        {
            if (13+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(13+getDefence());}

            special_power+=1;
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
        }
        else{
            if (8+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(8+getDefence());}
        }
    }
    @Override
    public void Special_power(Monster mymonster)
    {
        System.out.println("Special Power activated");
        setUsed_special(true);
        special_power=0;
    }

    @Override
    public void reduceHP_hero(double damage_gain)
    {
        this.addHP(-damage_gain);
        this.setDamage_taken(damage_gain);
    }
}

class Mage extends Hero
{
    private int special_power=-1;
    @Override
    public void attack(Monster mymonster)
    {
        if (special_power!=-1 && special_power<=3)
        {
            System.out.println("monster's HP reduced by 5%");
            mymonster.setHP((int) (mymonster.getHP()*0.95));
            mymonster.damageHP(5+getAttack());
            special_power+=1;
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
        }
        else{
            mymonster.damageHP(5+getAttack());
        }

        System.out.println("You chose to Attack with a value of: "+(getAttack()+5));
    }
    @Override
    public void defence(Monster mymonster,double last_attack)
    {
        if (special_power!=-1 && special_power<=3)
        {
            System.out.println("monster's HP reduced by 5%");
            mymonster.setHP((int) (mymonster.getHP()*0.95));
            if (5+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(5+getDefence());}
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
        }
        else{
            if (5+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(5+getDefence());}
        }

    }
    @Override
    public void defence_kn(Monster mymonster,double last_attack)
    {
        if (special_power!=-1 && special_power<=3)
        {
            System.out.println("monster's HP reduced by 5%");
            mymonster.setHP((int) (mymonster.getHP()*0.95));
            if (10+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(10+getDefence());}
            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
        }
        else{
            if (10+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(10+getDefence());}
        }

    }
    @Override
    public void reduceHP_hero(double damage_gain)
    {
        this.addHP(-damage_gain);
        this.setDamage_taken(damage_gain);
    }
    @Override
    public void Special_power(Monster mymonster)
    {
        System.out.println("Special Power activated");
        setUsed_special(true);
        special_power=0;
    }
}

class Thief extends Hero
{
    @Override
    public void attack(Monster mymonster)
    {
        mymonster.damageHP(6+getAttack());
        System.out.println("You chose to Attack with a value of: "+(getAttack()+6));
    }
    @Override
    public void defence(Monster mymonster,double last_attack)
    {

        if (4+getDefence()>last_attack)
        {
            this.addHP(last_attack);
        }
        else{this.addHP(4+getDefence());}
    }
    public void defence_kn(Monster mymonster,double last_attack)
    {

        if (9+getDefence()>last_attack)
        {
            this.addHP(last_attack);
        }
        else{this.addHP(9+getDefence());}
    }
    @Override
    public void reduceHP_hero(double damage_gain)
    {
        this.addHP(-damage_gain);
        this.setDamage_taken(damage_gain);
    }
    @Override
    public void Special_power(Monster mymonster)
    {
        System.out.println("Stole 30% HP of monster");
        this.addHP((int) (0.3*mymonster.getHP()));
        mymonster.setHP((int) (mymonster.getHP()-(0.3*mymonster.getHP())));
    }
}

class Healer extends Hero
{
    int special_power=-1;
    @Override
    public void attack(Monster mymonster)
    {
        if (special_power!=-1 && special_power<=3)
        {
            this.addHP((int) (this.getHP()*0.05));
            System.out.println("Your HP increased by 5%");
            mymonster.damageHP(4+getAttack());

            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
        }
        else{
            mymonster.damageHP(4+getAttack());
        }

        System.out.println("You chose to Attack with a value of: "+(getAttack()+4));
    }
    @Override
    public void defence(Monster mymonster,double last_attack)
    {
        if (special_power!=-1 && special_power<=3)
        {
            this.addHP((int) (this.getHP()*0.05));
            System.out.println("Your HP increased by 5%");
            if (8+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(8+getDefence());}

            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
        }
        else{
            if (8+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(8+getDefence());}
        }

    }
    @Override
    public void defence_kn(Monster mymonster,double last_attack)
    {
        if (special_power!=-1 && special_power<=3)
        {
            this.addHP((int) (this.getHP()*0.05));
            System.out.println("Your HP increased by 5%");
            if (13+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(13+getDefence());}

            if (special_power==3)
            {
                special_power=-1;
                setUsed_special(false);
            }
            special_power+=1;
        }
        else{
            if (13+getDefence()>last_attack)
            {
                this.addHP(last_attack);
            }
            else{this.addHP(13+getDefence());}
        }

    }
    @Override
    public void reduceHP_hero(double damage_gain)
    {
        this.addHP(-damage_gain);
        this.setDamage_taken(damage_gain);
    }
    @Override
    public void Special_power(Monster mymonster)
    {
        System.out.println("Special Power activated");
        setUsed_special(true);
        special_power=0;
    }

}






