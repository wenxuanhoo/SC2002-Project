package system;

import domain.Combatant;
import domain.Player;
import java.util.List;

//when smoke bomb is used, enemy attacks do 0 damage in the current turn and the next turn
public class SmokeBomb implements Item{
    private static final int DURATION = 2; //no damage for two turns

    @Override
    public void use(Player user, List<Combatant> combatants){
        if (user == null){
            return;
        }
        
        user.addEffect(new InvulnerabilityEffect(DURATION));
    }
    @Override
    public String getName(){
        return "Smoke Bomb";
    }
}
