package domain;


import java.util.ArrayList;
import java.util.List;
import mechanics.StatusEffect;


public abstract class Combatant{
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<StatusEffect> activeEffects = new ArrayList<>();
    
    private boolean isStunned = false; // State mutated by StatusEffects
    private boolean isInvulnerable = false;

    public int takeDamage(int amount){
        if (!isInvulnerable) {
            int prevHp = this.hp;
            this.hp = Math.max(0, this.hp - amount);
            return prevHp - this.hp;
        }
        return 0;
    }
    public void setInvulnerable(boolean invulnerable) {
        this.isInvulnerable = invulnerable;
    }
    public void heal(int amount){
        this.hp = Math.min(this.hp + amount, this.maxHp);
    }
    
    public void addEffect(StatusEffect effect){
        activeEffects.add(effect);
        effect.applyEffect(this); // Tell the effect to alter the Combatant's stats/state
    }
    
    public abstract String performTurn(List<Combatant> combatants);

    public void removeEffectsOfType(Class<? extends StatusEffect> type) {
        activeEffects.removeIf(effect -> {
            if (type.isInstance(effect)) {
                effect.removeEffect(this); // revert changes
                return true;
            }
            return false;
        });
    }
    
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
    public void setAttack(int attack){this.attack = attack;}
    public int getDefense(){return defense;}
    public void setDefense(int defense){this.defense = defense;}
    public int getHp(){return hp;}
    public int getMaxHp(){return maxHp;}
}
