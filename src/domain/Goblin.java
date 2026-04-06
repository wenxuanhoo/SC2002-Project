package domain;

public class Goblin extends Enemy{
    public Goblin(String name){
        this.name = name;
        this.maxHp = 55;
        this.hp = 55;
        this.attack = 35;
        this.defense = 15;
        this.speed = 25;
    }
}
