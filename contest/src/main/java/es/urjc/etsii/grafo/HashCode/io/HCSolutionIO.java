package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.HashCode.model.Person;
import es.urjc.etsii.grafo.io.serializers.SolutionSerializer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HCSolutionIO extends SolutionSerializer<HashCodeSolution, HashCodeInstance> {

    public HCSolutionIO(HCSolutionIOConfig config) {
        super(config);
    }

    @Override
    public void export(BufferedWriter bw, HashCodeSolution solution) throws IOException {
        var projects = solution.getProjectOrder();
        var assignments = solution.getAssignments();
        Map<Person, Integer> finishDate = new HashMap<>();
        int skipped = 0;
        StringBuilder sb = new StringBuilder();

        // write size - skipped
        //sb.append(projects.size()).append("\n");

        for(var project: projects){

            int startAt = -1;
            for (var person : assignments.get(project).values()) {
                finishDate.putIfAbsent(person, -1);
                startAt = Math.max(startAt, finishDate.get(person) + 1);
            }
            assert startAt != -1;

            int points = project.getScoreAt(startAt);
            if(points == 0){
                skipped++;
                continue;
            }

            for(var e: assignments.get(project).entrySet()){
                var skill = e.getKey();
                var person = e.getValue();
                finishDate.put(person, startAt + project.getDuration());
            }

            boolean flag = true;
            sb.append(project.getName()).append("\n");
            for(var skill: project.getSkills()){
                var person = assignments.get(project).get(skill);
                if (flag){
                    flag=false;
                    sb.append(person.getName());
                } else {
                    sb.append(" ").append(person.getName());
                }
            }
            sb.append("\n");
        }
        bw.write(projects.size() - skipped);
        bw.write("\n");
        bw.write(sb.toString());
    }
}
