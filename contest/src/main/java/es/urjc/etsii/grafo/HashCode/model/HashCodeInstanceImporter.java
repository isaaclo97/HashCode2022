package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.io.InstanceImporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashCodeInstanceImporter extends InstanceImporter<HashCodeInstance> {

    @Override
    public HashCodeInstance importInstance(BufferedReader reader, String filename) throws IOException {
        // Create and return instance object from file data
        // TODO parse all data from the given reader however I want
        // TIP You may use a Scanner if you prefer it to a Buffered Reader:
        // Scanner sc = new Scanner(reader);

        int contributors, projects;
        HashMap<String, HashMap<String,Integer>> becarios = new HashMap<>();
        HashMap<String, List<Skill>> proyectos = new HashMap<>();
        HashMap<String, Integer> project_D = new HashMap<>();
        HashMap<String, Integer> project_S = new HashMap<>();
        HashMap<String, Integer> project_B = new HashMap<>();
        HashMap<String, Integer> project_R = new HashMap<>();

        String line[] = reader.readLine().split(" ");
        contributors = Integer.parseInt(line[0]);
        projects = Integer.parseInt(line[1]);

        for(int i=0; i<contributors;i++){
            line = reader.readLine().split(" ");
            String user = line[0];
            int n = Integer.parseInt(line[1]);
            becarios.put(user,new HashMap<>());
            for(int j=0; j<n;j++){
                line = reader.readLine().split(" ");
                String language = line[0];
                int hability = Integer.parseInt(line[1]);
                becarios.get(user).put(language,hability);
            }
        }
        for(int i=0; i<projects;i++){
            line = reader.readLine().split(" ");
            String proyect = line[0];
            int D = Integer.parseInt(line[1]);
            int S = Integer.parseInt(line[2]);
            int B = Integer.parseInt(line[3]);
            int R = Integer.parseInt(line[4]);
            proyectos.put(proyect,new ArrayList<>());
            project_D.put(proyect,D);
            project_S.put(proyect,S);
            project_B.put(proyect,B);
            project_R.put(proyect,R);
            for(int j=0; j<R;j++){
                line = reader.readLine().split(" ");
                String language = line[0];
                int ability = Integer.parseInt(line[1]);
                proyectos.get(proyect).add(new Skill(language, ability));
            }
        }

        Map<String, Project> instance_projects = new HashMap<>();
        Map<String, Person> instance_people = new HashMap<>();

        for(var e: proyectos.entrySet()){
            String name = e.getKey();
            instance_projects.put(name, new Project(
                    name,
                    project_D.get(name),
                    project_S.get(name),
                    project_B.get(name),
                    e.getValue()
            ));
        }

        for (var e : becarios.entrySet()) {
            String name = e.getKey();
            instance_people.put(name, new Person(name, e.getValue()));
        }

        validate(filename, instance_projects, project_R);

        var instance = new HashCodeInstance(filename, instance_projects, instance_people);

        // IMPORTANT! Remember that instance data must be immutable from this point
        return instance;
    }

    private void validate(String instanceName, Map<String, Project> projects, HashMap<String, Integer> project_R) {
        for(var p: projects.values()){
            var name = p.getName();
            if(p.getSkills().size() != project_R.get(name)){
                throw new IllegalArgumentException(String.format("Failed validation, instance %s project %s", instanceName, name));
            }
        }
    }
}
