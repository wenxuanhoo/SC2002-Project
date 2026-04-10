package mechanics;

import domain.Combatant;
import java.util.List;

public class ShieldBashAction implements Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets) {
        if (user == null || targets == null || targets.isEmpty()){
            return user != null ? user.getName() + " has no target." : "No user for action.";
        }
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
