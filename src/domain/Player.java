package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Combatant {
    protected int cooldownTimer = 0;
    protected List<Item> inventory = new ArrayList<>();
    public void useItem(Item item, List<Combatant> combatants){
        if (inventory.contains(item)){
            item.apply(this, combatants);
            inventory.remove(item);
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
    public boolean hasItems(){
        return !inventory.isEmpty();
    }
    public abstract void useSpecialSkill(List<Combatant> combatants);
}
