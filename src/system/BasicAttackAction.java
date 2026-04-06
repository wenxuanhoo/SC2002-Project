package system;

import domain.Combatant;
import java.util.List;

public class BasicAttackAction implements Action{
    @Override
    public void execute(Combatant user, List<Combatant> targets){ //targets are chosen in boundary layer
        //validate that target has been chosen
        if (user == null || targets == null || targets.isEmpty()){
            return;
        }
        //get target for basic attack
        Combatant target = targets.get(0);
        if (target == null || target.isDefeated()){
            return;
        }
        //calculate damage done to target after basic attack
        int damage = Math.max(0, user.getAttack() - target.getDefense()); //ensure damage does not have negative value
        target.takeDamage(damage);
        }
}

