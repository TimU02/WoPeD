package org.woped.qualanalysis.soundness.marking;

import java.util.Set;
import java.util.SortedMap;
/**
 * This is a marking in a marking net compatible to the Woped reachability graph
 * 
 * @author Patrick Spies, Patrick Kirchgaessner, Joern Liebau, Enrico Moeller, Sebastian Fuss
 *
 */
public interface IMarking {
    /**
     * 
     * @return true, if the marking is the initial marking.
     */
    public boolean isInitial();

    /**
     * 
     * @return Map the key of the map is the id of an place. the value is the actual token count of the the place with the id of the key.
     */
    public SortedMap<String, Integer> getMarking();

    /**
     * 
     * @return all activated transitions.
     */
    public Set<String> getActivatedTransitions();

}