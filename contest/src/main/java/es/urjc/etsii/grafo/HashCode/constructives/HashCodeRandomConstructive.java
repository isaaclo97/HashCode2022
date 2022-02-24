package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.model.*;
import es.urjc.etsii.grafo.solver.create.Constructive;
import es.urjc.etsii.grafo.util.CollectionUtil;
import es.urjc.etsii.grafo.util.random.RandomManager;

import java.util.*;

public class HashCodeRandomConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    public HashCodeRandomConstructive(/*int repetitions*/){
        //this.repetitions = repetitions;
    }

    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {
        var rnd = RandomManager.getRandom();
        var instance = solution.getInstance();
        var users = instance.getPersons().values();

        // Project order
        var projectOrder = new ArrayList<>(instance.getProjects().values());
        var realProjectOrder = new ArrayList<Project>();

        CollectionUtil.shuffle(projectOrder);

        // Assignments
        Map<Project, Map<Skill, Person>> assignments = new HashMap<>();
        for (var project : projectOrder) {
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
                // discard
            }
        }

        solution.setAssigments(assignments);
        solution.setProjectOrder(realProjectOrder);
        solution.updateLastModifiedTime();
        return solution;
    }

}
