package domain;

import java.util.List;

public abstract class Enemy extends Combatant{
    @Override
    public void performTurn(List<Combatant> combatants){
        if (ifStunned()){
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