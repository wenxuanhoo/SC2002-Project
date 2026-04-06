package system;

import domain.Combatant;
import domain.Player;
import java.util.List;

public class PowerStone implements Item { //triggers special skill effect once, does not start or change the cooldown timer
    @Override
    public void use(Player user, List <Combatant> combatants){
        if (user == null){
            return;
        }

        user.forceSpecialSkill(combatants); // use force variant to bypass cooldown changes
    }
    @Override
    public String getName(){
        return "Power Stone";
    }
    @Override
    public boolean requiresEnemyTarget(Player user) {
        return !user.isSpecialSkillAoE(); // Only needs a target if the skill being forced needs a target!
    }
}
