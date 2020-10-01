import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

class Track implements Serializable
{
    private static final long serialVersionUID = 46L;
    private ArrayList<Tile> track;
    ArrayList<Tile> getTrack() {
        return track;
    }
    Track(int number_of_tiles,int dobara)
    {
        track=new ArrayList<>();
        if (dobara==0)
        {
            int[] choices=new int[5];
            Random rand=new Random();
            int whites=rand.nextInt(number_of_tiles/10)+((2*number_of_tiles)/10);
            choices[0]=whites;
            int vultures=rand.nextInt(number_of_tiles/10)+((2*number_of_tiles)/10);
            choices[1]=vultures;
            int crickets=rand.nextInt(number_of_tiles/10)+((2*number_of_tiles)/10);
            choices[2]=crickets;
            int lambda=number_of_tiles-whites-vultures-crickets;
            int trampolines=rand.nextInt((lambda)/4)+(2*lambda/4);
            choices[3]=trampolines;
            int snakes=number_of_tiles-whites-trampolines-vultures-crickets;
            choices[4]=snakes;
            int snake_att=rand.nextInt(9)+1;
            int vulture_att=rand.nextInt(9)+1;
            int cricket_att=rand.nextInt(9)+1;
            int trampoline_att=rand.nextInt(9)+1;
            System.out.println("Danger: There are "+snakes+" number of snakes"+" it moves you "+snake_att+" moves back");
            System.out.println("Danger: There are "+vultures+" number of vultures"+" it moves you "+vulture_att+" moves back");
            System.out.println("Danger: There are "+crickets+" number of crickets"+" it moves you "+cricket_att+" moves back");
            System.out.println("Good News: There are "+trampolines+" number of trampolines"+" it moves you "+trampoline_att+" moves front");
            System.out.println("Good News: There are "+whites+" number of whites");
            int dg;
            int pos=0;
            while (pos<number_of_tiles)
            {
                Tile t1;
                dg=rand.nextInt(5);
                if (dg==4 && choices[4]>0 )
                {
                    t1=new Snake(-snake_att);
                    track.add(t1);
                    choices[4]-=1;
                    pos+=1;
                }
                if (dg==1 && choices[1]>0)
                {
                    t1=new Vulture(-vulture_att);
                    track.add(t1);
                    choices[1]-=1;
                    pos+=1;
                }
                if (dg==2 && choices[2]>0)
                {
                    t1=new Cricket(-cricket_att);
                    track.add(t1);
                    choices[2]-=1;
                    pos+=1;
                }
                if (dg==3 && choices[3]>0)
                {
                    t1=new Trampoline(trampoline_att);
                    track.add(t1);
                    choices[3]-=1;
                    pos+=1;
                }
                if (dg==0 && choices[0]>0)
                {
                    t1=new White(0);
                    track.add(t1);
                    choices[0]-=1;
                    pos+=1;
                }
            }
        }
    }
    void move(int position, User usr){
        try
        {
            this.getTrack().get(position).setVisited(true);
            this.getTrack().get(position).shake(position,usr);
        }
        catch (VulturebiteException e)
        {
            System.out.println(e.getMessage());
        }
        catch (SnakebiteException e)
        {
            System.out.println(e.getMessage());
        }
        catch (CriketbiteException e)
        {
            System.out.println(e.getMessage());
        }
        catch (TrampolineException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
