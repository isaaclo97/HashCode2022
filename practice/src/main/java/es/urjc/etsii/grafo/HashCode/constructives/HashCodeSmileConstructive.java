package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.create.Constructive;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class HashCodeSmileConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {


    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        //Método malo para comprobar un método poco eficiente
        Random rnd = new Random(); //modify
        HashCodeSolution bestSolution = new HashCodeSolution(solution);
        double bestMark = -1;
        HashMap<String,Integer> timesLiked = new HashMap<>();

        for (ArrayList<String> strings : solution.getInstance().getIngredient_per_client()) {
            for (String string : strings) {
                timesLiked.putIfAbsent(string,0);
                timesLiked.compute(string,(s, integer) -> integer+1);
            }
        }
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(timesLiked.entrySet());
        Collections.sort(list, (o1, o2) -> -Integer.compare(o1.getValue(),o2.getValue()));
        int getMe = Math.max(1,(int) Math.sqrt(solution.getInstance().getIngredients().size()));
        bestSolution.getIngredients().clear();
        for (Map.Entry<String, Integer> stringIntegerEntry : list.subList(0, getMe)) {
            bestSolution.getIngredients().add(stringIntegerEntry.getKey());
        }
        bestSolution.updateLastModifiedTime();
        return bestSolution;
    }
}
