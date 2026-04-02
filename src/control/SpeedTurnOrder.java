package control;

import domain.Combatant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpeedTurnOrder implements TurnOrderStrategy {
    /**
     * takes a list of combatants, makes a copy, sorts that copy by speed and returns it. This determines who acts first each turn. 
     */

    @Override
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        List<Combatant> turnQueue = new ArrayList<>(combatants); // new copy so we don't scramble the master list. 
    turnQueue.sort(new Comparator<Combatant>()  { //sorts the list. Needs Comparator to know hot to compare two elements. 
        /**
         * anonymous class defined inline to be able to use the compare method
         */
        @Override
        public int compare(Combatant c1, Combatant c2) {
            /**
             * return: -1 if c1 < c2
             * return: 0 if c1 == c2
             * return +1 if c1 > c2
             */
            return Integer.compare(c2.getSpeed(), c1.getSpeed());
        }
    });
    return turnQueue;
    }
}

