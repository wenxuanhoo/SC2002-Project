package domain;

import java.util.List;
import domain.Player;

public abstract class Enemy extends Combatant{
    @Override
    public void performTurn(List<Combatant> combatants){
        if (isStunned()){
            System.out.println(name + " is stunned and cannot act.");
            return;
        }
        Combatant player = combatants.stream()
            .filter(c-> c instanceof Player)
            .findFirst()
            .orElse(null);

        if (player != null && !player.isDefeated()){
            int initialHp = player.getHp(); // Store initial HP to calculate damage later
            
            // Delegate the attack to the shared BasicAttackAction for extensibility
            new system.BasicAttackAction().execute(this, java.util.Collections.singletonList(player));
            
            int actualDamage = initialHp - player.getHp(); // Calculate damage dealt
            System.out.println(this.name + " attacks " + player.getName() + " for " + actualDamage + " damage!");
        }
    }
}