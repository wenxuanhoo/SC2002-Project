package mechanics;

import domain.Combatant;

public class ArcaneBlastEffect implements StatusEffect{
    private int remainingTurns;

    public ArcaneBlastEffect(int remainingTurns){
        this.remainingTurns = remainingTurns;
    }

    @Override
    public void applyEffect(Combatant target){
        target.setAttack(target.getAttack() + 10);
    }

    @Override
    public void removeEffect(Combatant target){
        target.setAttack(target.getAttack() - 10);
    }

    @Override
    public int getRemainingTurns(){
        return remainingTurns;
    }

    @Override
    public void decrementDuration(){
        remainingTurns--; 
    }

    @Override
    public boolean isExpired(){
        return remainingTurns <= 0; 
    }
}
