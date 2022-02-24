package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.solver.services.SolutionValidator;
import es.urjc.etsii.grafo.solver.services.ValidationResult;

public class HashcodeValidator extends SolutionValidator<HashCodeSolution, HashCodeInstance> {

    @Override
    public ValidationResult validate(HashCodeSolution hashCodeSolution) {
        return ValidationResult.ok();

        //return ValidationResult.fail("");

        //throw new UnsupportedOperationException();
    }
}
