

import java.util.ArrayList;

public class Undead extends Generation{

    public Undead() {
        this.setgName("Нежить");
        ArrayList<Creature> Unit = new ArrayList<>();
        Unit.add(new Necromant());
        Unit.add(new Hunter());
        Unit.add(new Hunter());
        Unit.add(new Hunter());
        Unit.add(new Zombi());
        Unit.add(new Zombi());
        Unit.add(new Zombi());
        Unit.add(new Zombi());
        this.unit = Unit;
        FantasyGame.Print_Log("Создан отряд нежети");

    }
    public class Necromant extends Creature implements mag {
            double defDamage=5;
            public Necromant() {
                this.setName("Некромант");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.UNDEAD);
            }

        @Override
        public boolean spel(Creature c) {
            c.setDamage(c.getDamage()*0.5);
            FantasyGame.Print_Log(this.getName()+" накладывает проклятие на "+c.getName());
            //setPremium(false);
            return true;
        }
        @Override
        public boolean turn(Creature c) {
            boolean rez = false;
            if (isPremium) setDamage(getDamage()*1.5);
            int ran = (int) (Math.random()*2);
            if (ran==0) rez=spel(c);
            else rez = attack(c);
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return rez;
        }

    }
    public class Hunter extends Creature implements archer {
        double defDamage=2;
        double defShot_damage=4; //default value
        double Shot_damage;
            public Hunter() {
                this.setName("Охотник");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setShot_damage(defShot_damage);
                this.setRace(Race.UNDEAD);
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
    public class Zombi extends Creature implements warrior {
        double defDamage=15;
            public Zombi() {
                this.setName("Зомби");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.UNDEAD);
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

