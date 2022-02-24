package es.urjc.etsii.grafo.HashCode.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && skills.equals(person.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, skills);
    }
}
