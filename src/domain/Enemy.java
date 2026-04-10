package domain;

import java.util.List;


public abstract class Enemy extends Combatant{
    @Override
    public String performTurn(List<Combatant> combatants){
        Combatant player = combatants.stream()
            .filter(c-> c instanceof Player)
            .findFirst()
            .orElse(null);

        if (player != null && !player.isDefeated()){
            return new mechanics.BasicAttackAction().execute(this, java.util.Collections.singletonList(player));
        }
        
        return this.name + " has no targets.";
    }
}