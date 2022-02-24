package es.urjc.etsii.grafo.HashCode.improve;

import es.urjc.etsii.grafo.HashCode.io.SolutionImprovedEvent;
import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.HashCode.model.Person;
import es.urjc.etsii.grafo.HashCode.model.Skill;
import es.urjc.etsii.grafo.solver.improve.Improver;
import es.urjc.etsii.grafo.solver.services.events.EventPublisher;

import java.util.*;

public class XXLocalSearch extends Improver<HashCodeSolution, HashCodeInstance> {

    @Override
    protected HashCodeSolution _improve(HashCodeSolution hashCodeSolution) {
        double currentScore = hashCodeSolution.getScore();
        boolean improved = true;

        while(improved) {
            improved = false;
            for(var p: hashCodeSolution.getProjectOrder()){
                Map<Skill, Person> setValue = new HashMap<>(hashCodeSolution.getAssignments().get(p));
                Map<String, Person> getPersons = new HashMap<>(hashCodeSolution.getInstance().getPersons());
                for(var e: setValue.keySet()){
                    for(var people: getPersons.values()) {
                        String skillAsigned = e.getName();
                        int levelAssigned = e.getN();
                        Person lastUser =  hashCodeSolution.getAssignments().get(p).get(skillAsigned);
                        if(people.getSkills().getOrDefault(skillAsigned,0) >= levelAssigned){
                            hashCodeSolution.getAssignments().get(p).put(e,people);
                        }
                        else{
                            continue;
                        }
                        if (currentScore  < hashCodeSolution.getScore()) {
                            currentScore = hashCodeSolution.getScore();
                            System.out.println(hashCodeSolution.getScore());
                            improved = true;
                            break;
                        }
                        hashCodeSolution.getAssignments().get(p).put(e,lastUser);
                    }
                    if(improved)
                        break;
                }
            }
        }
        //System.out.println(hashCodeSolution.getScore());
        return hashCodeSolution;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{}";
    }
}
