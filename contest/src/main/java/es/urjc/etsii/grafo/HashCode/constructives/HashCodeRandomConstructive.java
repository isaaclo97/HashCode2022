package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.create.Constructive;

public class HashCodeRandomConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        // IN --> Empty solution from solution(instance) constructor
        // OUT --> Feasible solution with an assigned score
        // TODO: Implement random constructive


        // Remember to call solution.updateLastModifiedTime() if the solution is modified without using moves!!
        solution.updateLastModifiedTime();
        //return solution;
        throw new UnsupportedOperationException("RandomConstructive not implemented yet");
    }
}
