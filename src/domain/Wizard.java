package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Wizard extends Player{
    public Wizard(String name){
        this.name = name;
        this.maxHp = 200;
        this.hp = 200;
        this.attack = 50;
        this.defense = 10;
        this.speed = 20;

    }

    @Override
    public String performTurn(List<Combatant> combatants){
        // Player turns are now managed by the Controller (BattleEngine) via UI loops
        // directly calling Action.execute(), decoupling Domain from Boundary!
        return "";
    }

    @Override
    public boolean isSpecialSkillAoE() {
        return true;
    }

    @Override
    public String useSpecialSkill(List<Combatant> combatants){
        if (isCoolDownReady()){
            List<Combatant> enemies = combatants.stream()
                .filter(c -> c instanceof Enemy && !c.isDefeated())
                .collect(Collectors.toList());
            String result = new mechanics.ArcaneBlastAction().execute(this, enemies);
            this.cooldownTimer = 3;
            return result;
        } else {
            return "Special skill is on cooldown!";
        }
    }
    
    @Override
    public String forceSpecialSkill(List<Combatant> combatants) {
        List<Combatant> enemies = combatants.stream()
            .filter(c -> c instanceof Enemy && !c.isDefeated())
            .collect(Collectors.toList());
        return new mechanics.ArcaneBlastAction().execute(this, enemies);
    }
}
