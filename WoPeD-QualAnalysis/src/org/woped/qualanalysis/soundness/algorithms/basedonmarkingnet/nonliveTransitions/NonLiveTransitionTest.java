package org.woped.qualanalysis.soundness.algorithms.basedonmarkingnet.nonliveTransitions;

import java.util.HashSet;
import java.util.Set;

import org.woped.qualanalysis.soundness.algorithms.basedonmarkingnet.AbstractMarkingNetTest;
import org.woped.qualanalysis.soundness.datamodel.TransitionNode;
import org.woped.qualanalysis.soundness.marking.Marking;
import org.woped.qualanalysis.soundness.marking.MarkingNet;

/**
 * indicates all non live transtions with exp. costs.
 * 
 * @author Patrick Spies, Patrick Kirchgaessner, Joern Liebau, Enrico Moeller, Sebastian Fuss
 * 
 */
public class NonLiveTransitionTest extends AbstractMarkingNetTest implements INonLiveTranstionTest {

	/**
	 * 
	 * @param mNet MarkingNet the algorithm is based on
	 */
    public NonLiveTransitionTest(MarkingNet mNet) {
        super(mNet);
    }

    /**
     * @see INonLiveTranstionTest#getNonLiveTransitions()
     */
    @Override
    public Set<TransitionNode> getNonLiveTransitions() {
        Set<TransitionNode> nonLiveTransitions = new HashSet<TransitionNode>();
        Set<Marking> markingsTemp;
        for (int i = 0; i < mNet.getTransitions().length; i++) {
            for (Marking marking : mNet.getMarkings()) {
                markingsTemp = new HashSet<Marking>();
                markingsTemp.add(marking);
                if (!marking.isTransitionReachable(mNet.getTransitions()[i], markingsTemp)) {
                    nonLiveTransitions.add(mNet.getTransitions()[i]);
                    break;
                }
            }
        }
        return nonLiveTransitions;

    }
}