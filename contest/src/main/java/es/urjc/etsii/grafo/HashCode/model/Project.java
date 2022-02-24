package es.urjc.etsii.grafo.HashCode.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Project {
    private final String name;
    private final int duration;
    private final int score;
    private final int bestBefore;
    private final List<Skill> skills;

    public Project(String name, int duration, int score, int bestBefore, List<Skill> skills) {
        this.name = name;
        this.duration = duration;
        this.score = score;
        this.bestBefore = bestBefore;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    public int getBestBefore() {
        return bestBefore;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public int getScoreAt(int day){
        if(day + duration < bestBefore){
            return score;
        } else {
            return Math.max(0, score + (bestBefore - (day + duration + 1)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
