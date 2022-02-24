package es.urjc.etsii.grafo.HashCode.experiments;

import es.urjc.etsii.grafo.HashCode.constructives.HashCodeRandomConstructive;
import es.urjc.etsii.grafo.HashCode.improve.XXLocalSearch;
import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.SolverConfig;
import es.urjc.etsii.grafo.solver.algorithms.Algorithm;
import es.urjc.etsii.grafo.solver.algorithms.SimpleAlgorithm;
import es.urjc.etsii.grafo.solver.services.AbstractExperiment;

import java.util.ArrayList;
import java.util.List;

public class ConstructiveExperiment extends AbstractExperiment<HashCodeSolution, HashCodeInstance> {

    public ConstructiveExperiment(SolverConfig solverConfig) {
        super(solverConfig);
    }

    @Override
    public List<Algorithm<HashCodeSolution, HashCodeInstance>> getAlgorithms() {
        var algorithms = new ArrayList<Algorithm<HashCodeSolution, HashCodeInstance>>();

        algorithms.add(new SimpleAlgorithm<>(new HashCodeRandomConstructive(100), new XXLocalSearch()));

        return algorithms;
    }
}
