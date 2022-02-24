package es.urjc.etsii.grafo.HashCode.model;

import java.util.HashMap;
import java.util.Map;

public class Person {
    private final String name;
    private final Map<String, Integer> skills;

    public Person(String name, Map<String, Integer> skills) {
        this.name = name;
        this.skills = skills;
    }


    public String getName() {
        return name;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public Person clone(){
        return new Person(name, new HashMap<>(skills));
    }

}
