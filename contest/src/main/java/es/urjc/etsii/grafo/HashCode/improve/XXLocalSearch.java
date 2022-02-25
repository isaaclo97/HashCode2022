package es.urjc.etsii.grafo.HashCode.improve;

import es.urjc.etsii.grafo.HashCode.GlobalBests;
import es.urjc.etsii.grafo.HashCode.io.SolutionImprovedEvent;
import es.urjc.etsii.grafo.HashCode.model.*;
import es.urjc.etsii.grafo.solver.improve.Improver;
import es.urjc.etsii.grafo.solver.services.events.EventPublisher;
import es.urjc.etsii.grafo.util.CollectionUtil;

import javax.xml.bind.annotation.XmlElementDecl;
import java.util.*;

public class XXLocalSearch extends Improver<HashCodeSolution, HashCodeInstance> {

    @Override
    protected HashCodeSolution _improve(HashCodeSolution hashCodeSolution) {
        double currentScore = hashCodeSolution.getScore();
        boolean improved = true;
        int iters = 0;
        while(improved) {
            improved = false;
            List<Project> proyects = hashCodeSolution.getProjectOrder();
            //CollectionUtil.shuffle(proyects);
            for(var p: proyects){
                Map<Skill, Person> setValue = new HashMap<>(hashCodeSolution.getAssignments().get(p));
                Map<String, Person> getPersons = new HashMap<>(hashCodeSolution.getInstance().getPersons());
                for(var e: setValue.keySet()){
                    for(var people: getPersons.values()) {
                        String skillAsigned = e.getName();
                        int levelAssigned = e.getN();
                        Person lastUser =  hashCodeSolution.getAssignments().get(p).get(e);
                        if(people.getSkills().getOrDefault(skillAsigned,0) >= levelAssigned){
                            hashCodeSolution.getAssignments().get(p).put(e,people);
                        }
                        else{
                            continue;
                        }
                        iters++;
                        //if(iters==5000) return hashCodeSolution;
                        if (currentScore  < hashCodeSolution.getScore()) {
                            currentScore = hashCodeSolution.getScore();
                            GlobalBests.checkIfBetter(hashCodeSolution);
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
        GlobalBests.checkIfBetter(hashCodeSolution);
        //System.out.println(hashCodeSolution.getScore());
        return hashCodeSolution;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{}";
    }
}
