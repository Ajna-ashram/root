public class Creature {
    String name;
    double HP;
    protected boolean isPremium;
    boolean doStep;
    boolean isAvalible;
    double damage;
    //double shot_damage;
    Race race;
    enum  Race {ELF,ORKS,PEOPLE,UNDEAD};

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {

        this.race = race;
    }




    public double getDamage() {
        return damage;
    }

//    public double getShot_damage() {
//        return shot_damage;
//    }

    public void setDamage(double damage) {

        this.damage = damage;
    }


    public void setAvalible() {
        if(HP>0) isAvalible=true;


        else {
            isAvalible = false;
            FantasyGame.Print_Log(this.name +" убит!");
        }
    }

    public boolean isAvalible() {

        return isAvalible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setDoStep(boolean doStep) {
        this.doStep = doStep;
    }

    public String getName() {
        return name;
    }

    public double getHP() {
        return HP;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isDoStep() {
        return doStep;
    }

    //Action methods

    public boolean attack(Creature c) {
        c.setHP(c.getHP() - this.getDamage());
        FantasyGame.Print_Log(this.getName() + " наносит удар силой " + this.getDamage() + " по " + c.getName()+"("+c.getHP()+")");
        c.setAvalible();
        //this.setPremium(false);
        return true;
    }
    public boolean turn(Creature c){
        FantasyGame.Print_Log("пустой ход :( не должно быть видно");
        return false;
    }
}
