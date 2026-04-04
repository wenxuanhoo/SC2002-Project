package system;

import domain.Combatant;

public class StunEffect implements StatusEffect{
    private int remainingTurns;

    public StunEffect(int remainingTurns){
        this.remainingTurns = remainingTurns;
    }

    @Override
    public void applyEffect(Combatant target){
        //no direct stats change here
    }

    @Override
    public void removeEffect(Combatant target){
        //nothing to undo 
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
        return remainingTurns <= 0; //check if remaining turns is less than or equal to 0
    }
}
