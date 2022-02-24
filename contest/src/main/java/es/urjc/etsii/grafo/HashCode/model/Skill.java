package es.urjc.etsii.grafo.HashCode.model;

public class Skill {
    private final String name;
    private final int n;

    public Skill(String name, int n) {
        this.name = name;
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public int getN() {
        return n;
    }
}
