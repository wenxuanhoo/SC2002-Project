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
    public void reduceCooldown(){
        if (cooldownTimer > 0){
            cooldownTimer--;
        }
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
}
