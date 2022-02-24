package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.io.Instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HashCodeInstance extends Instance {

    int contributors, projects;
    HashMap<String,ArrayList<Pair<Integer,Integer>>> becarios = new HashMap<>();
    public HashCodeInstance(String name, BufferedReader reader) throws IOException {
        super(name);
        String line[] = reader.readLine().split(" ");
        contributors = Integer.parseInt(line[0]);
        projects = Integer.parseInt(line[1]);

        for(int i=0; i<contributors;i++){
            line = reader.readLine().split(" ");
            String user = line[0];
            int n = Integer.parseInt(line[1]);
            for(int j=0; j<n;j++){
                line = reader.readLine().split(" ");
                String language = line[0];
                int hability = Integer.parseInt(line[1]);
            }
        }
        for(int i=0; i<projects;i++){
            line = reader.readLine().split(" ");
            String proyect = line[0];
            int D = Integer.parseInt(line[1]);
            int S = Integer.parseInt(line[2]);
            int B = Integer.parseInt(line[3]);
            int R = Integer.parseInt(line[4]);
        }
    }
}
