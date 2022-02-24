package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.io.Instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HashCodeInstance extends Instance {

    int contributors, projects;
    HashMap<String,HashMap<String,Integer>> becarios = new HashMap<>();
    HashMap<String,HashMap<String,Integer>> proyectos = new HashMap<>();
    HashMap<String, Integer> project_D = new HashMap<>();
    HashMap<String, Integer> project_S = new HashMap<>();
    HashMap<String, Integer> project_B = new HashMap<>();
    HashMap<String, Integer> project_R = new HashMap<>();

    public HashCodeInstance(String name, BufferedReader reader) throws IOException {
        super(name);
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
            proyectos.put(proyect,new HashMap<>());
            project_D.put(proyect,D);
            project_S.put(proyect,S);
            project_B.put(proyect,B);
            project_R.put(proyect,R);
            for(int j=0; j<R;j++){
                line = reader.readLine().split(" ");
                String language = line[0];
                int hability = Integer.parseInt(line[1]);
                proyectos.get(proyect).put(language,hability);
            }
        }
    }

    public int getContributors() {
        return contributors;
    }

    public int getProjects() {
        return projects;
    }

    public HashMap<String, HashMap<String, Integer>> getBecarios() {
        return becarios;
    }

    public HashMap<String, HashMap<String, Integer>> getProyectos() {
        return proyectos;
    }

    public HashMap<String, Integer> getProject_D() {
        return project_D;
    }

    public HashMap<String, Integer> getProject_S() {
        return project_S;
    }

    public HashMap<String, Integer> getProject_B() {
        return project_B;
    }

    public HashMap<String, Integer> getProject_R() {
        return project_R;
    }

}
