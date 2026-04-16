package mechanics;

import domain.Combatant;

public class StunEffect extends StatusEffect {

    public StunEffect(int remainingTurns) {
        super(remainingTurns);
    }

    @Override
    public void applyEffect(Combatant target) {
        target.setStunned(true); // Alter the target's actual state
    }

    @Override
    public void removeEffect(Combatant target) {
        target.setStunned(false); // Revert the target's state when the effect expires
    }
}
