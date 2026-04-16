package domain;

import java.util.List;

public class Warrior extends Player{
    public Warrior(String name){
        this.name = name;
        this.maxHp = 260;
        this.hp = 260;
        this.attack = 40;
        this.defense = 20;
        this.speed = 30;
    }
    @Override
    public boolean isSpecialSkillAoE() {
        return false;
    }

    @Override
    public String useSpecialSkill(List<Combatant> combatants){
        if (isCoolDownReady()){
            String result = new mechanics.ShieldBashAction().execute(this, combatants);
            this.cooldownTimer = 3;
            return result;
        } else {
            return "Special skill is on cooldown!";
        }
    }
    
    @Override
    public String forceSpecialSkill(List<Combatant> combatants) {
        return new mechanics.ShieldBashAction().execute(this, combatants);
    }
}