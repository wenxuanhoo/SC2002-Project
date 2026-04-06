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
    
    protected boolean isStunned = false; // State mutated by StatusEffects

    public void takeDamage(int amount){
        this.hp = Math.max(0, this.hp - amount);
    }
    public void heal(int amount){
        this.hp = Math.min(this.hp + amount, this.maxHp);
    }
    
    public void addEffect(StatusEffect effect){
        activeEffects.add(effect);
        effect.applyEffect(this); // Tell the effect to alter the Combatant's stats/state
    }
    
    public abstract void performTurn(List<Combatant> combatants);
    
    public void updateEffects(){
        activeEffects.removeIf(effect -> {
            effect.decrementDuration();
            if (effect.isExpired()) {
                effect.removeEffect(this); // Tell the effect to revert its changes
                return true;
            }
            return false;
        });
    }
    
    public boolean isDefeated(){
        return this.hp <= 0;
    }
    
    // Instead of looping through instances, simply check the state variable
    public boolean isStunned(){
        return isStunned;
    }
    
    public void setStunned(boolean stunned) {
        this.isStunned = stunned;
    }
    
    public int getSpeed(){return speed;}
    public String getName(){return name;}
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getHp(){return hp;}
    public int getMaxHp(){return maxHp;}
}
