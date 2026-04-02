package control;

import domain.Combatant;
import java.util.List;

public interface TurnOrderStrategy {
    /**
     * Determines the turn order in a game.
     * 
     * @param players The list of players in the game.
     * @return The ordered list of players for the current turn.
     */
    List<Combatant> determineTurnOrder(List<Combatant> combatants);

}