package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.create.Constructive;
import es.urjc.etsii.grafo.util.random.RandomManager;

public class HashCodeRandomConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    private int iters;
    public HashCodeRandomConstructive(int iters){
        this.iters = iters;
    }

    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        var rnd = RandomManager.getRandom();



        throw new UnsupportedOperationException("HashCodeRandomConstructive::construct");
        //return solution;
    }
}
