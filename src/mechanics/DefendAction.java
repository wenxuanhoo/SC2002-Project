package mechanics;

import domain.Combatant;
import java.util.List;

public class DefendAction extends Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets) {
        if (user == null) {
            return "No user for action.";
        }
        // Remove previous DefendEffect to prevent stacking indefinite armor
        user.removeEffectsOfType(DefendEffect.class);
        // Duration 2 ensures the effect survives the enemy turns and expires exactly at the end of the user's next turn.
        user.addEffect(new DefendEffect(2)); 
        return user.getName() + " is defending! (+10 Defense)";
    }
}
