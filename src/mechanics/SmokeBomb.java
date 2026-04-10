package mechanics;

import domain.Combatant;
import domain.Player;
import java.util.List;

//when smoke bomb is used, enemy attacks do 0 damage in the current turn and the next turn
public class SmokeBomb implements Item{
    private static final int DURATION = 3; // Ensures survival through the current enemy wave AND the next one

    @Override
    public void use(Player user, List<Combatant> combatants){
        if (user == null){
            return;
        }
        
        user.removeEffectsOfType(InvulnerabilityEffect.class);
        user.addEffect(new InvulnerabilityEffect(DURATION));
    }
    @Override
    public String getName(){
        return "Smoke Bomb";
    }
    @Override
    public boolean requiresEnemyTarget(Player user) {
        return false;
    }
}
