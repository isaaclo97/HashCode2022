package es.urjc.etsii.grafo.HashCode.improve;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solver.improve.Improver;

import java.util.ArrayList;

public class LSSawps extends Improver<HashCodeSolution, HashCodeInstance> {

    @Override
    protected HashCodeSolution _improve(HashCodeSolution hashCodeSolution) {
        double mark = hashCodeSolution.getScore();
        boolean improve = true;
        ArrayList<String> addIngredient = new ArrayList<>();
        ArrayList<String> removeIngredient = new ArrayList<>();
        while(improve) {
            improve = false;
            addIngredient.clear();
            removeIngredient.clear();
            for(String s: hashCodeSolution.getInstance().getIngredients()){
                if(hashCodeSolution.getIngredients().contains(s)) removeIngredient.add(s);
                else addIngredient.add(s);
            }
            for (String addI : addIngredient) {
                for (String removeI : removeIngredient) {
                    hashCodeSolution.getIngredients().remove(removeI);
                    hashCodeSolution.getIngredients().add(addI);
                    double mark2 = hashCodeSolution.getScore();
                    if (Double.compare(mark2, mark) > 0) {
                        improve = true;
                        mark = mark2;
                        System.out.println("BL " + hashCodeSolution.getInstance().getName() + " " + mark);
                        hashCodeSolution.updateLastModifiedTime();
                        hashCodeSolution.printSolution();
                    }else{
                        hashCodeSolution.getIngredients().add(removeI);
                        hashCodeSolution.getIngredients().remove(addI);
                    }
                    if (improve) break;
                }
                if (improve) break;
            }
        }
        return hashCodeSolution;
    }

    @Override
    public String toString() {
        return "LSSwaps{}";
    }
}
