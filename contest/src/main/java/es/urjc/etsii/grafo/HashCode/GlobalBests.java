package es.urjc.etsii.grafo.HashCode;

import es.urjc.etsii.grafo.HashCode.io.SolutionImprovedEvent;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.services.events.EventPublisher;

import java.util.HashMap;
import java.util.Map;

public class GlobalBests {
    private static final Map<String, Integer> globalBests = new HashMap<>();

    public synchronized static void checkIfBetter(HashCodeSolution solution){
        int currentScore = (int) Math.round(solution.getScore());
        String name = solution.getInstance().getName();
        globalBests.putIfAbsent(name, 0);
        int bestScore = globalBests.get(name);
        if(currentScore > bestScore){
            var event = new SolutionImprovedEvent(solution, bestScore);
            EventPublisher.getInstance().publishEvent(event);
            globalBests.put(name, currentScore);
        }
    }
}
