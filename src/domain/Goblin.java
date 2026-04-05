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
public class Wolf extends Enemy{
    public Wolf(String name){
        this.name = name
        this.maxHp = 40;
        this.hp = 40;
        this.attack = 45;
        this.defense = 5;
        this.speed = 35;
    }
}