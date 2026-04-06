package domain;

public class Wolf extends Enemy{
    public Wolf() {
        this("Wolf");
    }
    
    public Wolf(String name){
        this.name = name;
        this.maxHp = 40;
        this.hp = 40;
        this.attack = 45;
        this.defense = 5;
        this.speed = 35;
    }
}
