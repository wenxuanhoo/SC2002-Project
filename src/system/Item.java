package system;

import java.util.List;
import domain.Combatant;
import domain.Player;

public interface Item{
    void use(Player user, List<Combatant> combatants);
    String getName();
}

