package system;

import domain.Combatant;
import domain.Player;
import java.util.List;

public class SmokeBomb implements Item{
    private static final int DURATION = 2;

    @Override
    public void use(Player user, List<Combatant> combatants){
        if (user == null){
            return;
        }
        //when smoke bomb is used, enemy attacks do 0 damage in teh current turn and the next turn
        user.addEffect(new InvulnerabilityEffect(DURATION));
    }
    @Override
    public String getName(){
        return "Smoke Bomb";
    }
}
