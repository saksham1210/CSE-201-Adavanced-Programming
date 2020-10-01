import java.io.Serializable;

class User implements Serializable
{
    private static final long serialVersionUID = 43L;
    private String name;
    private int snake_bites=0;
    private int cricket_bites=0;
    private int vulture_bites=0;
    private int dice_roll=0;
    private int present_position;
    private Track mytrack;
    private int number_of_tiles;

    @Override
    public boolean equals(Object u1)
    {
        if(!(u1 instanceof User)){
            return  false;
        }
        else {
            User user = (User) u1;
            if (this.name.equals(user.name) && this.getDice_roll()==user.getDice_roll() && this.number_of_tiles==user.getNumber_of_tiles()) {
                return true;
            }
        }
        return false;
    }

    int getNumber_of_tiles() {
        return number_of_tiles;
    }

    int getPresent_position() {
        return present_position;
    }

    void setPresent_position(int present_position) {
        this.present_position = present_position;
    }

    Track getMytrack() {
        return mytrack;
    }

    void setMytrack(Track mytrack) {
        this.mytrack = mytrack;
    }

    int getDice_roll() {
        return dice_roll;
    }

    void setDice_roll() {
        this.dice_roll +=1;
    }

    void setSnake_bites() {
        this.snake_bites +=1;
    }

    void setCricket_bites() {
        this.cricket_bites += 1;
    }

    void setVulture_bites() {
        this.vulture_bites +=1;
    }

    String getName() {
        return name;
    }

    int getCricket_bites() {
        return cricket_bites;
    }

    int getVulture_bites() {
        return vulture_bites;
    }

    int getSnake_bites() {
        return snake_bites;
    }
    User(String name,int number_of_tiles)
    {
      this.name = name;
      this.present_position=0;
      this.number_of_tiles=number_of_tiles;
    }

}
