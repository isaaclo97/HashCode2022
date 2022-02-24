package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.solution.Solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class HashCodeSolution extends Solution<HashCodeSolution, HashCodeInstance> {


    public HashCodeSolution(HashCodeInstance ins) {
        super(ins);
        // TODO initialize fields
    }

    /**
     * Clone constructor
     *
     * @param s Solution to clone
     */
    public HashCodeSolution(HashCodeSolution s) {
        super(s);
        // TODO copy fields

    }


    @Override
    public HashCodeSolution cloneSolution() {
        // You do not need to modify this method
        // Call clone constructor
        return new HashCodeSolution(this);
    }

    @Override
    protected boolean _isBetterThan(HashCodeSolution other) {
        return getScore()>other.getScore();
    }

    /**
     * Get the current solution score.
     * The difference between this method and recalculateScore is that
     * this result can be a property of the solution, or cached,
     * it does not have to be calculated each time this method is called
     *
     * @return current solution score as double
     */
    @Override
    public double getScore() {

        // TODO bad, optimize
        return recalculateScore();
    }

    /**
     * Recalculate solution score and validate current solution state
     * You must check that no constraints are broken, and that all costs are valid
     * The difference between this method and getScore is that we must recalculate the score from scratch,
     * without using any cache/shortcuts.
     * DO NOT UPDATE CACHES / MAKE SURE THIS METHOD DOES NOT HAVE SIDE EFFECTS
     *
     * @return current solution score as double
     */
    @Override
    public double recalculateScore() {
        // Implement full score calculation
        throw new UnsupportedOperationException("HashCodeSolution recalculateScore() not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Generate me");
    }

}
