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
        if (isStunned()){
            System.out.println(name + "is stunned and skips the turn!");
        }
    }
    @Override
    public void useSpecialSkill(List<Combatant> combatants){
        if (isCoolDownReady()){
            Combatant targetEnemy = combatants.stream()
                .filter(c -> c instanceof Enemy && !c.isDefeated())
                .findFirst()
                .orElse(null);

            if (targetEnemy != null) {
                System.out.println(this.name + " uses Shield Bash on " + targetEnemy.getName() + "!");
                shieldBash(targetEnemy);
                
                this.cooldownTimer = 3;
            } else {
                System.out.println("No valid targets for Shield Bahs.");
            } 
        } else {
            System.out.println("Special skill is on cooldown!");
        }
    }
    private void shieldBash(Combatant target){
        int damage = Math.max(0, this.attack - target.getDefense());
        target.takeDamage(damage);
        target.addEffect(new system.StunEffect(2));
    }
}