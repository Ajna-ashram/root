

import java.util.ArrayList;

public class People extends Generation{

    public People() {
        this.setgName("Люди");
        ArrayList<Creature> Unit = new ArrayList<>();
        Unit.add(new MagHuman());
        Unit.add(new Arbalet());
        Unit.add(new Arbalet());
        Unit.add(new Arbalet());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        this.unit = Unit;
        FantasyGame.Print_Log("Создан отряд людей");

    }

    public class MagHuman extends Creature implements mag {
        double defDamage=4;
        public MagHuman() {
            this.setName("Магчел");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.PEOPLE);
        }

        @Override
        public boolean spel(Creature c) {
            c.setPremium(true);
            FantasyGame.Print_Log(this.getName()+" накладывает улучшение на "+c.getName());
            //setPremium(false);
            return true;
        }

        @Override
        public boolean turn(Creature c) {
            boolean rez = false;
            if (isPremium) setDamage(getDamage()*1.5);
            if (c.race.equals(Creature.Race.ORKS)||c.race.equals(Race.UNDEAD)){
                rez = attack(c);
            }
            else if (c.race.equals(Race.ELF)||c.race.equals(Race.PEOPLE))rez=spel(c);
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return rez;
        }
    }
    public class Arbalet extends Creature implements archer {
        double defDamage=3;
        double defShot_damage=5; //default value
        double Shot_damage;
        public Arbalet() {
            this.setName("Арбалетчик");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setShot_damage(defShot_damage);
            this.setRace(Race.PEOPLE);
        }

        @Override
        public boolean shot(Creature c) {

            c.setHP(c.getHP()-this.getShot_damage());
            FantasyGame.Print_Log(this.getName()+" выпускает стрелу и наносит ущерб "+this.Shot_damage+" "+c.getName()+"("+c.getHP()+")");
            c.setAvalible();
            //setPremium(false);
            return true;

        }

        @Override
        public boolean turn(Creature c) {
            if (isPremium) {
                setDamage(getDamage() * 1.5);
                setShot_damage(getShot_damage()*1.5);
            }
            int ran = (int) (Math.random()*2);
            if (ran==0) shot(c);
            else attack(c);
            setPremium(false);
            setDamage(defDamage);
            setShot_damage(defShot_damage);
            setDoStep(true);
            return true;
        }

        public double getShot_damage() {
            return Shot_damage;
        }

        public void setShot_damage(double shot_damage) {

            Shot_damage = shot_damage;
        }



    }
    public class Warrior extends Creature implements warrior {
        double defDamage=15;
        public Warrior() {
            this.setName("Рыцарь");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.PEOPLE);
        }
        @Override
        public boolean turn(Creature c) {
            if (isPremium) {
                setDamage(getDamage() * 1.5);
            }
            attack(c);
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return true;
        }


    }
}
