package system;

import domain.Combatant;
import domain.Player;
import java.util.List;

public class Potion implements Item{
    private static final int HEAL_AMOUNT = 100;

    @Override
    public void use(Player user, List<Combatant> combatants){
        if (user == null){
            return;
        }
        user.heal(HEAL_AMOUNT);
    }
    @Override
    public String getName(){
        return "Potion";
    }
}
