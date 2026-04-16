package mechanics;

import domain.Combatant;
import java.util.List;

public class BasicAttackAction extends Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets){ //targets are chosen in boundary layer
        //validate that target has been chosen
        String error = validateInputs(user, targets);
        if (error != null) return error;
        //get target for basic attack
        Combatant target = targets.get(0);
        if (target == null || target.isDefeated()){
            return user.getName() + " has no target.";
        }
        //calculate damage done to target after basic attack
        int damage = Math.max(0, user.getAttack() - target.getDefense()); //ensure damage does not have negative value
        target.takeDamage(damage);
        return user.getName() + " used Basic Attack on " + target.getName() + "!";
        }
}

