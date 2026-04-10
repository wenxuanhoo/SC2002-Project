package mechanics;

import domain.Combatant;
import java.util.List;

public class ArcaneBlastAction implements Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets) {
        if (user == null || targets == null || targets.isEmpty()){
            return user != null ? user.getName() + " has no targets." : "No user for action.";
        }
        
        StringBuilder sb = new StringBuilder(user.getName() + " uses Arcane Blast hit everyone!");
        int snapshotAttack = user.getAttack(); // Snapshot attack BEFORE the loop so all enemies take the same damage
        
        for (Combatant enemy : targets){
            if (enemy.isDefeated()) continue;
            int damage = Math.max(0, snapshotAttack - enemy.getDefense());
            enemy.takeDamage(damage);
            if (enemy.isDefeated()){
                user.addEffect(new ArcaneBlastEffect(999));
                sb.append(" Arcane Blast defeated ").append(enemy.getName()).append("! Wizard Attack + 10.");
            }
        }
        return sb.toString();
    }
}
