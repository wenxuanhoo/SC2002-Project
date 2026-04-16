package mechanics;

import domain.Combatant;
import java.util.List;

public class ShieldBashAction extends Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets) {
        String error = validateInputs(user, targets);
        if (error != null) return error;
        Combatant target = targets.get(0);
        if (target == null || target.isDefeated()){
            return user.getName() + " has no target.";
        }

        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);
        target.removeEffectsOfType(StunEffect.class);
        target.addEffect(new StunEffect(2));
        return user.getName() + " uses Shield Bash on " + target.getName() + "!";
    }
}
