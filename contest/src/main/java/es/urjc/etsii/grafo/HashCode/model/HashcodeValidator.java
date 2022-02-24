package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.solver.services.SolutionValidator;
import es.urjc.etsii.grafo.solver.services.ValidationResult;

import java.util.HashMap;
import java.util.Map;

public class HashcodeValidator extends SolutionValidator<HashCodeSolution, HashCodeInstance> {

    @Override
    public ValidationResult validate(HashCodeSolution hashCodeSolution) {


        Map<String,Person> curlvl = new HashMap<>();
        hashCodeSolution.getInstance().getPersons().forEach((s, person) -> {
            curlvl.put(person.getName(),person.clone());
        });

        for (Project curProj : hashCodeSolution.projectOrder) {
            Map<String, Integer> maxLvlcurProj = new HashMap<>();
            for (Map.Entry<Skill, Person> skillPersonEntry : hashCodeSolution.getAssignments().get(curProj).entrySet()) {
                Person assigned = skillPersonEntry.getValue();
                for (Map.Entry<String, Integer> currentSkills : curlvl.get(assigned.getName()).getSkills().entrySet()) {
                    //Important to get current levels not inital levels.
                    String skillName = currentSkills.getKey();
                    int lvl = currentSkills.getValue();
                    maxLvlcurProj.putIfAbsent(skillName,lvl);
                    maxLvlcurProj.computeIfPresent(skillName, (s, integer) -> Math.max(integer,lvl) );
                }
            }
            for (Map.Entry<Skill, Person> skillPersonEntry : hashCodeSolution.getAssignments().get(curProj).entrySet()) {
                Skill req = skillPersonEntry.getKey();
                Person assigned = skillPersonEntry.getValue();
                if (req.getN() <= assigned.getSkills().getOrDefault(req.getName(),0)){
                    //Esta bien pero es mas fWacil pensarlo asi,
                } else if (req.getN()-1 == assigned.getSkills().getOrDefault(req.getName(),0) && maxLvlcurProj.getOrDefault(req.getName(),0)>=req.getN()){
                    curlvl.get(assigned.getName()).getSkills().putIfAbsent(req.getName(),0);
                    curlvl.get(assigned.getName()).getSkills().compute(req.getName(),(s, integer) -> integer + 1);
                } else ValidationResult.fail("Not valid level assigned");
            }
        }


        //return ValidationResult.ok();

        //return ValidationResult.fail("");

        throw new UnsupportedOperationException();
    }
}
