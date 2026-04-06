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
    public void performTurn(List<Combatant> combatants){
        // Player turns are now managed by the Controller (BattleEngine) via UI loops
        // directly calling Action.execute(), decoupling Domain from Boundary!
    }
    
    @Override
    public boolean isSpecialSkillAoE() {
        return false;
    }

    private void performSpecialSkillLogic(List<Combatant> combatants) {
        if (combatants != null && !combatants.isEmpty()) {
            Combatant targetEnemy = combatants.get(0);
            System.out.println(this.name + " uses Shield Bash on " + targetEnemy.getName() + "!");
            shieldBash(targetEnemy);
        } else {
            System.out.println("No valid targets for Shield Bash.");
        } 
    }

    @Override
    public void useSpecialSkill(List<Combatant> combatants){
        if (isCoolDownReady()){
            performSpecialSkillLogic(combatants);
            this.cooldownTimer = 3;
        } else {
            System.out.println("Special skill is on cooldown!");
        }
    }
    
    @Override
    public void forceSpecialSkill(List<Combatant> combatants) {
        performSpecialSkillLogic(combatants);
        // cooldownTimer is NOT reset here
    }
    private void shieldBash(Combatant target){
        int damage = Math.max(0, this.attack - target.getDefense());
        target.takeDamage(damage);
        target.addEffect(new system.StunEffect(2));
    }
}