

import java.util.ArrayList;

public class Orks extends Generation{

        public Orks() {
            this.setgName("Орки");
            ArrayList<Creature> Unit = new ArrayList<>();
            Unit.add(new Shaman());
            Unit.add(new Archer());
            Unit.add(new Archer());
            Unit.add(new Archer());
            Unit.add(new Goblin());
            Unit.add(new Goblin());
            Unit.add(new Goblin());
            Unit.add(new Goblin());
            this.unit = Unit;
            FantasyGame.Print_Log("Создан отряд орков");

        }

        public class Shaman extends Creature implements mag {
            public Shaman() {
                this.setName("Шаман");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setRace(Race.ORKS);
            }

            @Override
            public boolean spel(Creature c) {
//                boolean test = c instanceof Orks.Shaman;
//                //System.out.println("=="+c.getName()+" "+c.getClass().get);
//                System.out.println("проверка "+c.getName()+ test);
                if (c.race.equals(Race.PEOPLE)||c.race.equals(Race.ELF)){
                    c.setPremium(false);
                    FantasyGame.Print_Log(this.getName()+" снимает улучшение с "+c.getName());
                }
                else if (c.race.equals(Race.ORKS)||c.race.equals(Race.UNDEAD)){
                    c.setPremium(true);
                    FantasyGame.Print_Log(this.getName()+" накладывает улучшение на "+c.getName());
                }
                //setPremium(false);
                return true;
            }
            @Override
            public boolean turn(Creature c) {
                boolean rez = false;
                rez=spel(c);
                setPremium(false);
                //setDamage(defDamage);
                setDoStep(true);
                return rez;
            }

        }
        public class Archer extends Creature implements archer {
            double defDamage=2;
            double defShot_damage=3; //default value
            double Shot_damage;

            public Archer() {
                this.setName("Лучник орк");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setShot_damage(defShot_damage);
                this.setRace(Race.ORKS);
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
        public class Goblin extends Creature implements warrior {
            double defDamage = 20;

            public Goblin() {
                this.setName("Гоблин");
                this.setHP(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.ORKS);
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


