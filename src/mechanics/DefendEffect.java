package mechanics;

import domain.Combatant;

public class DefendEffect extends StatusEffect {

    public DefendEffect(int remainingTurns) {
        super(remainingTurns);
    }

    @Override
    public void applyEffect(Combatant target) {
        // Add 10 to defense dynamically
        target.setDefense(target.getDefense() + 10);
    }

    @Override
    public void removeEffect(Combatant target) {
        // Ensure to remove the 10 defense when the timer expires
        target.setDefense(target.getDefense() - 10);
    }
}
