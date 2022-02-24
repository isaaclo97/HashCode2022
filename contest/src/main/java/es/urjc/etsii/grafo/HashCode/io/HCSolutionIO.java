package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.io.serializers.SolutionSerializer;

import java.io.BufferedWriter;
import java.io.IOException;

public class HCSolutionIO extends SolutionSerializer<HashCodeSolution, HashCodeInstance> {

    public HCSolutionIO(HCSolutionIOConfig config) {
        super(config);
    }

    @Override
    public void export(BufferedWriter bw, HashCodeSolution solution) throws IOException {
        var projects = solution.getProjectOrder();
        var assignments = solution.getAssignments();

        bw.write(projects.size() + "\n");
        for(var project: projects){
            boolean flag = true;
            bw.write(project.getName() + "\n");
            for(var skill: project.getSkills()){
                var person = assignments.get(project).get(skill);
                if (flag){
                    flag=false;
                    bw.write(person.getName());
                } else {
                    bw.write(" " + person.getName());
                }
            }
            bw.write("\n");
        }
    }
}
