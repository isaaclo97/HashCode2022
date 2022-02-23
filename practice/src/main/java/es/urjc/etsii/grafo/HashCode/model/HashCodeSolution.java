package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.solution.Solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class HashCodeSolution extends Solution<HashCodeSolution, HashCodeInstance> {

    /**
     * Initialize solution from instance
     *
     * @param ins
     */

    private HashSet<String> ingredients;
    private boolean update;
    private double mark;

    public HashCodeSolution(HashCodeInstance ins) {
        super(ins);
        ingredients = new HashSet<>();
        update = true;
    }

    /**
     * Clone constructor
     *
     * @param s Solution to clone
     */
    public HashCodeSolution(HashCodeSolution s) {
        super(s);
        ingredients = new HashSet<>(s.getIngredients());
        update = s.update;
    }

    public void copy(HashCodeSolution s) {
        ingredients = new HashSet<>(s.getIngredients());
        update = s.update;
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
        double res = 0;
        for(int i=0; i<this.getInstance().C;i++){
            boolean flag = true;
            for(String in:this.getInstance().getIngredient_per_client().get(i)){
                if(!ingredients.contains(in)) { flag=false; break; }
            }
            for(String in:this.getInstance().getIngredient_dislike_per_client().get(i)){
                if(ingredients.contains(in)) { flag=false; break; }
            }
            if(flag) res++;
        }
        return mark = res;
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
        update = true;
        return mark=getScore();
    }

    @Override
    public String toString() {
        return "HashCodeSolution{" +
                "ingredients=" + ingredients +
                ", update=" + update +
                ", mark=" + mark +
                '}';
    }

    /**
     * Generate a string representation of this solution. Used when printing progress to console,
     * show as minimal info as possible
     *
     * @return Small string representing the current solution (Example: id + score)
     */


    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public void printSolution(){
        try{
            FileWriter fw=new FileWriter("./solutions/"+this.getInstance().getName()+'_'+this.getScore());
            BufferedWriter bw = new BufferedWriter(fw);
            //System.out.print(ingredients.size());
            bw.write(Integer.toString(ingredients.size()));
            for(String ing:ingredients){
                //System.out.print(" " + ing);
                bw.write(" " + ing);
            }
            //System.out.println();
            //System.out.println(this.getScore());
            bw.close();
            fw.close();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
}
