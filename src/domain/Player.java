package domain;

import java.util.ArrayList;
import java.util.List;
import system.Item;

public abstract class Player extends Combatant {
    protected int cooldownTimer = 0;
    protected List<Item> inventory = new ArrayList<>();
    public void useItem(Item item, List<Combatant> combatants){
        if (inventory.contains(item)){
            item.use(this, combatants); 
            inventory.remove(item);
        }
    }
    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }
    public List<Item> getInventory() {
        return inventory;
    }
    public void reduceCooldown(){
        if (cooldownTimer > 0){
            cooldownTimer--;
        }
    }
    @Override
    public void updateEffects() {
        super.updateEffects();
        reduceCooldown();
    }
    public boolean isCoolDownReady(){
        return cooldownTimer == 0;
    }
    public int getCooldownTimer() {
        return cooldownTimer;
    }
    public boolean hasItems(){
        return !inventory.isEmpty();
    }
    public abstract void useSpecialSkill(List<Combatant> combatants);
    public abstract void forceSpecialSkill(List<Combatant> combatants);
    public abstract boolean isSpecialSkillAoE();
}
