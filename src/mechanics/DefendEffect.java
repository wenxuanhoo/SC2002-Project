package mechanics;

import domain.Combatant;

public class DefendEffect implements StatusEffect {
    private int remainingTurns;

    public DefendEffect(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    @Override
    public void applyEffect(Combatant target){
        // Add 10 to defense dynamically
        target.setDefense(target.getDefense() + 10);
    }
    
    @Override
    public void removeEffect(Combatant target){
        // Ensure to remove the 10 defense when the timer expires
        target.setDefense(target.getDefense() - 10);
    }

    @Override
    public int getRemainingTurns(){
        return remainingTurns;
    }

    @Override
    public void decrementDuration(){
        remainingTurns--; //decrease number of remaining turns by 1
    }

    @Override
    public boolean isExpired(){
        return remainingTurns <= 0; 
    }
}
