package mechanics;

import domain.Combatant;
import java.util.List;

public class ArcaneBlastAction extends Action {
    @Override
    public String execute(Combatant user, List<Combatant> targets) {
        String error = validateInputs(user, targets);
        if (error != null) return error;
        
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
