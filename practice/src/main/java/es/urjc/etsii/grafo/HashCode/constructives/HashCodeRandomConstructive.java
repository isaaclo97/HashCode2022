package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.improve.LSSawps;
import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.create.Constructive;
import es.urjc.etsii.grafo.util.random.RandomManager;
import org.bouncycastle.crypto.prng.RandomGenerator;

import java.util.ArrayList;
import java.util.Random;

public class HashCodeRandomConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    private int iters;
    private LSSawps ls;
    public HashCodeRandomConstructive(int iters, LSSawps ls){
        this.iters = iters;
        this.ls = ls;
    }
    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        //Método malo para comprobar un método poco eficiente
        Random rnd = new Random(); //modify
        HashCodeSolution bestSolution = new HashCodeSolution(solution);
        double bestMark = -1;
        for(int iter = 0; iter<iters; iter++) {
            int number = rnd.nextInt(solution.getInstance().getIngredients().size()) + 1;
            Object[] ingredients = solution.getInstance().getIngredients().toArray();
            solution.getIngredients().clear();
            for (int i = 0; i < number; i++) {
                int num = rnd.nextInt(solution.getInstance().getIngredients().size());
                solution.getIngredients().add((String)ingredients[num]);
            }
            double mark = solution.getScore();
            if (Double.compare(mark, bestMark) > 0) {
                bestSolution.copy(solution);
                bestMark = mark;
                System.out.println(solution.getInstance().getName() + " " + bestMark);
                bestSolution.updateLastModifiedTime();
                //bestSolution.printSolution();
            }
        }
        bestSolution.printSolution();
        ls.improve(bestSolution);
        return bestSolution;
    }
}
