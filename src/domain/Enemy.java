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
        Combatant target = combatants.stream()
            .filter(c -> c instanceof Player && !c.isDefeated())
            .findFirst()
            .orElse(null);
        if (target != null){
            int damage = Math.max(0, this.attack - target.getDefense());
            target.takeDamage(damage);
            System.out.println(name + " performs Basic Attack on " + target.getName() + " for " + damage + " damage ");
        }
    }
}