package es.urjc.etsii.grafo.HashCode.improve;

import es.urjc.etsii.grafo.HashCode.io.SolutionImprovedEvent;
import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.improve.Improver;
import es.urjc.etsii.grafo.solver.services.events.EventPublisher;

import java.util.ArrayList;

public class XXLocalSearch extends Improver<HashCodeSolution, HashCodeInstance> {

    @Override
    protected HashCodeSolution _improve(HashCodeSolution hashCodeSolution) {
        double currentScore = hashCodeSolution.getScore();
        boolean improved = true;

        while(improved) {
            improved = false;
            throw new UnsupportedOperationException("XXLocalSearch::improve not implemented yet");
        }
        return hashCodeSolution;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{}";
    }
}
