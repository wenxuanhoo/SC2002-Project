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
            int calcDamage = Math.max(0, this.attack - player.getDefense());
            int actualDamage = player.takeDamage(calcDamage);
            System.out.println(this.name + " attacks " + player.getName() + " for " + actualDamage + " damage!");
        }
    }
}