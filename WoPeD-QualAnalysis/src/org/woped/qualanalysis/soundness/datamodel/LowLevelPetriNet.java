package org.woped.qualanalysis.soundness.datamodel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.woped.qualanalysis.soundness.algorithms.generic.INodeNet;

/**
 * this class represents a low level petri net
 * 
 * @author Patrick Spies, Patrick Kirchgaessner, Joern Liebau, Enrico Moeller, Sebastian Fuss
 * 
 */
public class LowLevelPetriNet implements INodeNet<AbstractNode> {
    // declaration

    private Map<String, PlaceNode> places = new TreeMap<String, PlaceNode>(new PlaceSort());
    private Map<String, TransitionNode> transitions = new TreeMap<String, TransitionNode>();

    public LowLevelPetriNet() {

    }

    /**
     * 
     * @param node adds the provide node to this low level petri net
     * @return true if node was added.
     */
    public boolean addNode(AbstractNode node) {
        boolean result = false;

        if (node instanceof TransitionNode) {
            // true if node does not exist in map
            result = !transitions.containsKey(node.getId());
            getTransitionNode((TransitionNode) node);
        } else {
            if (node instanceof PlaceNode) {
                // true if node does not exist in map
                result = !places.containsKey(node.getId());
                getPlaceNode((PlaceNode) node);
            }
        }
        return result;

    }

    /**
     * adds all nodes of the provided set of nodes.
     * 
     * @param nodes to be added.
     * @return true
     */
    public boolean addNodes(Set<AbstractNode> nodes) {
        boolean result = true;

        for (AbstractNode node : nodes) {
            result = result & addNode(node);
        }
        return true;
    }

    /**
     * if a node with the id of the provided node already exists the existing node will returned. if the provided node does not exist, the provided node will
     * returned
     * 
     * @param node example.
     * @return node from map.
     */
    public PlaceNode getPlaceNode(PlaceNode node) {
        PlaceNode node2return;
        node2return = places.get(node.getId());

        if (node2return == null) {
            // add the provided node to map
            node2return = node;
            places.put(node.getId(), node);
        }

        return node2return;
    }

    /**
     * @return the placeNodes
     */
    public Set<PlaceNode> getPlaces() {
        Set<PlaceNode> places = new HashSet<PlaceNode>();
        for (String key : this.places.keySet()) {
            places.add(this.places.get(key));
        }
        return places;
    }

    /**
     * if a node with the id of the provided node already exists the existing node will returned. if the provided node does not exist, the provided node will
     * returned
     * 
     * @param node example.
     * @return node from map.
     */
    public TransitionNode getTransitionNode(TransitionNode node) {
        TransitionNode node2return;
        node2return = transitions.get(node.getId());

        if (node2return == null) {
            // add the provided node to map
            node2return = node;
            transitions.put(node.getId(), node);
        }

        return node2return;
    }

    /**
     * @return the transitionNodes
     */
    public Set<TransitionNode> getTransitions() {
        Set<TransitionNode> transitions = new HashSet<TransitionNode>();
        for (String key : this.transitions.keySet()) {
            transitions.add(this.transitions.get(key));
        }
        return transitions;
    }

    @Override
    public Set<AbstractNode> getAllContainedNodes() {
        Set<AbstractNode> set = new HashSet<AbstractNode>();
        set.addAll(places.values());
        set.addAll(transitions.values());
        return set;
    }
}