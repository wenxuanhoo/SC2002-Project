package system;

import domain.Combatant;
import java.util.List;
import domain.Player;

public class SpecialSkillAction implements Action{
    @Override
    public void execute(Combatant user, List<Combatant> targets){ //targets are chosen in boundary layer
        //validate that target has been chosen
        if (user == null || targets == null || targets.isEmpty()){
            return;
        }

        if (!(user instanceof Player player)){
            return;
        }

        if (!player.isCoolDownReady()){
            return;
        }

        player.useSpecialSkill(targets);
    }
}