package mechanics;

import domain.Combatant;
import java.util.List;

public interface Action{
    String execute(Combatant user, List<Combatant> targets);
}