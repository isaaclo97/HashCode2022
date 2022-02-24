package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.solution.Solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashCodeSolution extends Solution<HashCodeSolution, HashCodeInstance> {

    List<Project> projectOrder = new ArrayList<>();
    Map<Project, Map<Skill, Person>> assignments = new HashMap<>();

    public HashCodeSolution(HashCodeInstance ins) {
        super(ins);
        // TODO initialize fields
    }

    /**
     * Clone constructor
     *
     * @param s Solution to clone
     */
    public HashCodeSolution(HashCodeSolution s) {
        super(s);
        this.projectOrder = new ArrayList<>(s.getProjectOrder());
        for(var e: s.getAssignments().entrySet()){
            this.assignments.put(e.getKey(), new HashMap<>(e.getValue()));
        }
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
        // TODO bad, optimize
        return recalculateScore();
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
        int score = 0;
        Map<Person, Integer> finishDate = new HashMap<>();

        for(var p: projectOrder){
            int startAt = -1;
            for (var person : this.assignments.get(p).values()) {
                finishDate.putIfAbsent(person, -1);
                startAt = Math.max(startAt, finishDate.get(person) + 1);
            }
            assert startAt != -1;

            for(var e: this.assignments.get(p).entrySet()){
                var skill = e.getKey();
                var person = e.getValue();
                finishDate.put(person, startAt + p.getDuration());
            }

            score += p.getScoreAt(startAt);
        }


        return score;
    }


    @Override
    public String toString() {
        return String.format("Score: %s, data: %s", getScore(), this.projectOrder);
    }

    public void setAssigments(Map<Project, Map<Skill, Person>> assignments) {
        this.assignments = assignments;
    }

    public void setProjectOrder(ArrayList<Project> projectOrder) {
        this.projectOrder = projectOrder;
    }

    public List<Project> getProjectOrder() {
        return projectOrder;
    }

    public Map<Project, Map<Skill, Person>> getAssignments() {
        return assignments;
    }
}
