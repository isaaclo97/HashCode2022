package es.urjc.etsii.grafo.HashCode.constructives.grasp;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.solution.EagerMove;
import es.urjc.etsii.grafo.solver.create.grasp.GRASPListManager;

import java.util.ArrayList;
import java.util.List;

public class HashCodeListManager extends GRASPListManager<HashCodeListManager.HashCodeGRASPMove, HashCodeSolution, HashCodeInstance> {

    /**
     * Generate initial candidate list. The list will be sorted if necessary by the constructive method.
     * @param solution Current solution
     * @return a candidate list
     */
    @Override
    public List<HashCodeGRASPMove> buildInitialCandidateList(HashCodeSolution solution) {
        var list = new ArrayList<HashCodeGRASPMove>();

        // TODO Generate a list with all valid movements for current solution
        // GRASP ends when CL is empty

        return list;
    }

    /**
     * Update candidate list after each movement. The list will be sorted by the constructor.
     * @param solution Current solution, move has been already applied
     * @param move     Chosen move
     * @param index index of the chosen move in the candidate list
     * @param candidateList original candidate list
     * @return an UNSORTED candidate list, where the best candidate is on the first position and the worst in the last
     */
    @Override
    public List<HashCodeGRASPMove> updateCandidateList(HashCodeSolution solution, HashCodeGRASPMove move, List<HashCodeGRASPMove> candidateList, int index) {
        // List can be partially updated / modified
        // recalculating from scratch is an ok start and can be optimized later if necessary
        return buildInitialCandidateList(solution);
    }


    public static class HashCodeGRASPMove extends EagerMove<HashCodeSolution, HashCodeInstance> {
        public HashCodeGRASPMove(HashCodeSolution solution) {
            super(solution);
        }

        @Override
        protected void _execute() {
            // TODO Apply changes to solution if movement is executed
            // this.s --> reference to current solution
            throw new UnsupportedOperationException("_execute() in HashCodeListManager not implemented yet");

        }

        @Override
        public double getValue() {
            // TODO How much does o.f. value change if we apply this movement?
            throw new UnsupportedOperationException("getValue() in HashCodeListManager not implemented yet");
        }

        @Override
        public boolean improves() {
            // TODO: Choose implementation
            // If maximizing: return DoubleComparator.isPositive(this.score)
            // If minimizing: return DoubleComparator.isNegative(this.score)
            throw new UnsupportedOperationException("improves() in HashCodeListManager not implemented yet");
        }

        @Override
        public String toString() {
            // TODO Use IDE to generate this method after all properties are defined
            throw new UnsupportedOperationException("toString() in HashCode not implemented yet");
        }

        @Override
        public boolean equals(Object o) {
            // TODO Use IDE to generate this method after all properties are defined
            throw new UnsupportedOperationException("equals() in HashCode not implemented yet");
        }

        @Override
        public int hashCode() {
            // TODO Use IDE to generate this method after all properties are defined
            throw new UnsupportedOperationException("hashCode() in HashCode not implemented yet");
        }

        @Override
        public boolean isValid() {
            // A move is not valid if it leaves the solution in an unfeasible state
            // If the movement is not valid, it should probably not be in the candidate list.
            return true;
        }
    }
}
