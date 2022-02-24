package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.services.events.types.MorkEvent;

public class SolutionImprovedEvent extends MorkEvent {
    private final HashCodeSolution solution;
    private final double oldScore;

    public SolutionImprovedEvent(HashCodeSolution solution, double oldScore) {
        this.solution = solution;
        this.oldScore = oldScore;
    }

    public HashCodeSolution getSolution() {
        return solution;
    }

    public double getOldScore() {
        return oldScore;
    }
}
