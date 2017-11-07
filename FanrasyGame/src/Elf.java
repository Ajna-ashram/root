

import java.util.ArrayList;

public class Elf extends Generation{

    public Elf() {
        this.setgName("Ельфы");
        ArrayList<Creature> Unit = new ArrayList<>();
        Unit.add(new MagElf());
        Unit.add(new Archer());
        Unit.add(new Archer());
        Unit.add(new Archer());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        Unit.add(new Warrior());
        this.unit = Unit;
        FantasyGame.Print_Log("Создан отряд эьфов");

    }

    public class MagElf extends Creature implements mag {
        double defDamage=10;


        public MagElf() {

            this.setName("Маг");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.ELF);
        }

        @Override
        public boolean turn(Creature c) {
            boolean rez = false;
            if (isPremium) setDamage(getDamage()*1.5);
            if (c.race.equals(Creature.Race.ORKS)||c.race.equals(Race.UNDEAD)){
                rez = attack(c);

            }
            else if (c.race.equals(Race.ELF)||c.race.equals(Race.PEOPLE))rez=spel(c);
            //int ran = (int) (Math.random()*1);
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return rez;
        }

        @Override
        public boolean spel(Creature c) {
            c.setPremium(true);
            FantasyGame.Print_Log(this.getName()+" накладывает улучшение на "+c.getName());
            //setPremium(false);
            return true;
        }

    }
    public class Archer extends Creature implements archer {

        double defDamage=3;
        double defShot_damage=7; //default value
        double Shot_damage;
        public Archer() {
            this.setName("Лучник эльф");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setShot_damage(defShot_damage);
            this.setRace(Race.ELF);
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

        @Override
        public boolean shot(Creature c) {
            c.setHP(c.getHP()-this.getShot_damage());
            FantasyGame.Print_Log(this.getName()+" выпускает стрелу и наносит ущерб "+this.Shot_damage+" "+c.getName()+"("+c.getHP()+")");

            c.setAvalible();
            //setPremium(false);
            return true;
        }

    }
    public class Warrior extends Creature implements warrior {

        double defDamage=15;
        public Warrior() {
            this.setName("Воин");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.ELF);

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
