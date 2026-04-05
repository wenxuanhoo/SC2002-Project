package domain;


import java.util.ArrayList;
import java.util.List;
import system.StatusEffect;


public abstract class Combatant{
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<StatusEffect> activeEffects = new ArrayList<>();

    public void takeDamage(int amount){
        this.hp = Math.max(0, this.hp - amount);
    }
    public void heal(int amount){
        this.hp = Math.min(this.hp + amount, this.maxHp);
    }
    public void addEffect(StatusEffect effect){
        activeEffects.add(effect);
    }
    public abstract void performTurn(List<Combatant> combatants);
    public void updateEffects();{
        activeEffects.removeIf(effect -> effect.decrementDuration() <= 0);
    }
    public boolean isDefeated(){
        return this.hp <= 0;
    }
    public boolean isStunned(){
        return activeEffects.stream().anyMatch(e -> e.getName().equals("Stun"));
    }
    public int getSpeed(){return speed;}
    public String getName(){return name;}
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getHp(){return hp;}
}
