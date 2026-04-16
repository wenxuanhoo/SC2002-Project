package mechanics;

import domain.Combatant;

public abstract class StatusEffect {
    private int remainingTurns;

    protected StatusEffect(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public abstract void applyEffect(Combatant target);
    public abstract void removeEffect(Combatant target);

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void decrementDuration() {
        remainingTurns--;
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }
}