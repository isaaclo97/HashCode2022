package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.GlobalBests;
import es.urjc.etsii.grafo.HashCode.model.*;
import es.urjc.etsii.grafo.solver.create.Constructive;
import es.urjc.etsii.grafo.util.CollectionUtil;
import es.urjc.etsii.grafo.util.random.RandomManager;

import java.util.*;

public class HashCodeSergioIsaacConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    public HashCodeSergioIsaacConstructive(/*int repetitions*/){
        //this.repetitions = repetitions;
    }

    private class Candidate{
        Project p;
        double value;
        public Candidate(Project p, double value){
            this.p = p;
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }
    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        var rnd = RandomManager.getRandom();
        var instance = solution.getInstance();
        var users = instance.getPersons().values();
        int cnt = 0;
        // Project order
        ArrayList<Candidate> projectOrder = new ArrayList<>();
        var realProjectOrder = new ArrayList<Project>();

        for (var project : solution.getInstance().getProjects().keySet()) {
            String name = solution.getInstance().getProjects().get(project).getName();
            int duration =  solution.getInstance().getProjects().get(project).getDuration();
            int score = solution.getInstance().getProjects().get(project).getScore();
            int skills = solution.getInstance().getProjects().get(project).getSkills().size();
            int bestBefore = solution.getInstance().getProjects().get(project).getBestBefore();
            double value = (duration*skills)*1.0/score;
            projectOrder.add(new Candidate(solution.getInstance().getProjects().get(project),value));
        }
        projectOrder.sort(Comparator.comparingDouble(Candidate::getValue));
        var projectOrderNuevo = new ArrayList<Project>();
        for (var p : projectOrder) {
            projectOrderNuevo.add(p.p);
        }
        // Assignments
        Map<Project, Map<Skill, Person>> assignments = new HashMap<>();
        for (var project : projectOrderNuevo) {
            var projectAssignments = new HashMap<Skill, Person>();

            Set<Person> usedPeople = new HashSet<>();

            for(var skill: project.getSkills()){
                var validUsers = new ArrayList<Person>();
                for(var user: users){
                    int skillLevel = user.getSkills().getOrDefault(skill.getName(), 0);
                    if(!usedPeople.contains(user) && skillLevel >= skill.getN()){
                        validUsers.add(user);
                    }
                }
                if(validUsers.isEmpty()){
                    break;
                }
                var chosenUser = CollectionUtil.pickRandom(validUsers);
                usedPeople.add(chosenUser);
                projectAssignments.put(skill, chosenUser);
            }
            if(project.getSkills().size() == projectAssignments.size()){
                realProjectOrder.add(project);
                assignments.put(project, projectAssignments);
            } else {
                cnt++;
                // discard
            }
        }

        solution.setAssigments(assignments);
        solution.setProjectOrder(realProjectOrder);
        solution.updateLastModifiedTime();
        //System.out.println(cnt);
        GlobalBests.checkIfBetter(solution);
        return solution;
    }

}
