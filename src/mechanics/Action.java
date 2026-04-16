package mechanics;

import domain.Combatant;
import java.util.List;

public abstract class Action {
    /**
     * Validates that the action has a valid user and at least one target.
     * @return an error message if invalid, or null if valid.
     */
    protected String validateInputs(Combatant user, List<Combatant> targets) {
        if (user == null) return "No user for action.";
        if (targets == null || targets.isEmpty()) return user.getName() + " has no target.";
        return null;
    }

    public abstract String execute(Combatant user, List<Combatant> targets);
}