package es.urjc.etsii.grafo.HashCode.constructives;

import es.urjc.etsii.grafo.HashCode.GlobalBests;
import es.urjc.etsii.grafo.HashCode.model.*;
import es.urjc.etsii.grafo.solver.create.Constructive;
import es.urjc.etsii.grafo.util.CollectionUtil;
import es.urjc.etsii.grafo.util.random.RandomManager;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class HashCodeSmileConstructive extends Constructive<HashCodeSolution, HashCodeInstance> {

    public HashCodeSmileConstructive(/*int repetitions*/){
        //this.repetitions = repetitions;
    }

    @Override
    public HashCodeSolution construct(HashCodeSolution solution) {

        Map<String,Person> curlvl = new HashMap<>();
        solution.getInstance().getPersons().forEach((s, person) -> {
            curlvl.put(person.getName(),person.clone());
        });

        var rnd = RandomManager.getRandom();
        var instance = solution.getInstance();

        // Project order
        var projectOrder = new ArrayList<>(instance.getProjects().values());
        Collections.sort(projectOrder,(o1, o2) -> {
            return Integer.compare(o1.startBefore(),o2.startBefore());
        });
        var realProjectOrder = new ArrayList<Project>();


        HashMap<Person,Integer> availableAt = new HashMap<>();


        // Assignments
        Map<Project, Map<Skill, Person>> assignments = new HashMap<>();
        projectFor: for (var project : projectOrder) {
            HashMap<String, Integer> maxSkill = new HashMap<>();
            HashSet<Person> used = new HashSet<>();
            var projectAssignments = new HashMap<Skill, Person>();
            // Asignar personas primero.
            // Despues comprobar si existe algun maxskill para mentrizar.
            ArrayList<Skill> requiredSkills = new ArrayList<>(project.getSkills());
            Collections.sort(requiredSkills,(o1, o2) -> {
                return -Integer.compare(o1.getN(),o2.getN());
            }); // Skills mas grandes primero.

            int fastStart = -1;
            for(var skillReq: requiredSkills) {
                var existingUsers = new ArrayList<>(curlvl.values());
                //curlvl.get(u1.getName())
                Collections.sort(existingUsers, (u1, u2) -> {
                    return Integer.compare(u1.getSkills().getOrDefault(skillReq.getName(), 0),
                            u2.getSkills().getOrDefault(skillReq.getName(), 0));
                });
                // Lowest skill first
                boolean assigned = false;
                for (Person user : existingUsers) {
                    if (used.contains(user))continue;
                    if (availableAt.getOrDefault(user.getName(), 0) < project.startBefore()) {
                        if (curlvl.get(user.getName()).getSkills().getOrDefault(skillReq.getName(), 0) == skillReq.getN() - 1 &&
                                maxSkill.get(skillReq.getName()) >= skillReq.getN()) {
                            projectAssignments.put(skillReq, user);
                            fastStart = Math.max(fastStart,availableAt.getOrDefault(user.getName(), 0));
                            assigned = true;
                            user.getSkills().forEach((s, integer) -> {
                                maxSkill.putIfAbsent(s,integer);
                                maxSkill.computeIfPresent(s,(s1, integer1) -> Math.max(integer1,integer));
                            });
                            used.add(user);
                            break;
                        } else if (curlvl.get(user.getName()).getSkills().getOrDefault(skillReq.getName(), 0) >= skillReq.getN()) {
                            projectAssignments.put(skillReq, user);
                            fastStart = Math.max(fastStart,availableAt.getOrDefault(user.getName(), 0));
                            assigned = true;
                            user.getSkills().forEach((s, integer) -> {
                                maxSkill.putIfAbsent(s,integer);
                                maxSkill.computeIfPresent(s,(s1, integer1) -> Math.max(integer1,integer));
                            });
                            used.add(user);
                            break;
                        }
                    }
                }
            }
            if(project.getSkills().size() == projectAssignments.size()){
                //Update available at.
                for (Person value : projectAssignments.values()) {
                    availableAt.put(value,fastStart+ project.getDuration()+1);
                }
                //Update skill lvls.
                for (Map.Entry<Skill, Person> skillPersonEntry : projectAssignments.entrySet()) {
                    Skill req = skillPersonEntry.getKey();
                    Person assigned = skillPersonEntry.getValue();
                    if (req.getN() < assigned.getSkills().getOrDefault(req.getName(),0)){
                        //Esta bien pero es mas fWacil pensarlo asi
                    } else if (req.getN()-2 < assigned.getSkills().getOrDefault(req.getName(),0)){
                        curlvl.get(assigned.getName()).getSkills().putIfAbsent(req.getName(),0);
                        curlvl.get(assigned.getName()).getSkills().compute(req.getName(),(s, integer) -> integer + 1);
                    }
                }
                realProjectOrder.add(project);
                assignments.put(project, projectAssignments);
            } else {
                // discard
            }
        }

        solution.setAssigments(assignments);
        solution.setProjectOrder(realProjectOrder);
        solution.updateLastModifiedTime();

        GlobalBests.checkIfBetter(solution);
        return solution;
    }

}
