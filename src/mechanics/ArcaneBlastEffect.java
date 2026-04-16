package mechanics;

import domain.Combatant;

public class ArcaneBlastEffect extends StatusEffect {

    public ArcaneBlastEffect(int remainingTurns) {
        super(remainingTurns);
    }

    @Override
    public void applyEffect(Combatant target) {
        target.setAttack(target.getAttack() + 10);
    }

    @Override
    public void removeEffect(Combatant target) {
        target.setAttack(target.getAttack() - 10);
    }
}
