package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Wizard extends Player{
    private int bonusAttack = 0;
    public Wizard(String name){
        this.name = name;
        this.maxHp = 260;
        this.hp = 200;
        this.attack = 50;
        this.defense = 10;
        this.speed = 20;

    }
    @Override
    public void useSpecialSkill(List<Combatant> combatants){
        if (isCooldownReady()){
            List<Combatant> enemies = combatants.stream();
                filter(c -> c instanceof Enemy && !c.isDefeated());
                collect(Collectors.toList());

            arcaneBlast(enemies);
            this.cooldownTimer = 3;
        }
    }
    private void arcaneBlast(List<Combatant> enemies){
        int currentAttack = this.attack + this.bonusAttack;
        for (Combatant enermy : enemies){
            int damage = Math.max(0, currentAttack - enermy.getDefense());
            enemy.takeDamage(damage);
            if (enemy.isDefeated()){
                this.bonusAttack += 10;
                System.out.println("Arcane Blast defeated " + enemy.getName() +"!Wizard Attack + 10.");
            }
        }
    }
    public int getBonusAttack(){
        return bonusAttack;
    }
    @Override
    public int getAttack(){
        return this.attack + this.bonusAttack;
    }
}
