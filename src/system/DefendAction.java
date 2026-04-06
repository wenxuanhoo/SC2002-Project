package system;

import domain.Combatant;
import java.util.List;

public class DefendAction implements Action {
    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        if (user == null) {
            return;
        }
        // Duration 2 ensures the effect survives the enemy turns and expires exactly at the end of the user's next turn.
        user.addEffect(new DefendEffect(2)); 
    }
}
