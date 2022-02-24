package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.io.Instance;

import java.io.IOException;
import java.util.*;

public class HashCodeInstance extends Instance {

    private final Map<String, Project> projects;
    private final Map<String, Person> persons;


    public HashCodeInstance(String name, Map<String, Project> instance_projects, Map<String, Person> instance_people) throws IOException {
        super(name);
        this.projects = instance_projects;
        this.persons = instance_people;
    }

    public Map<String, Project> getProjects() {
        return projects;
    }

    public Map<String, Person> getPersons() {
        return persons;
    }
}
