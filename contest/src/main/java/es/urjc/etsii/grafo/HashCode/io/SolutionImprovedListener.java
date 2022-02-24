package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.solver.services.events.MorkEventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Component
public class SolutionImprovedListener {

    private static final Logger log = Logger.getLogger(SolutionImprovedListener.class.getName());
    private static final String FOLDER = "realtime";
    private final HCSolutionIO solutionIO;

    public SolutionImprovedListener(HCSolutionIO solutionIO) {
        this.solutionIO = solutionIO;
        new File(FOLDER).mkdirs();
    }

    @MorkEventListener
    public void onSolutionImproved(SolutionImprovedEvent event){
        var solution = event.getSolution();
        var newBest = solution.getScore();
        log.info(String.format("New best: %s, Δ: %s", newBest, Math.abs(event.getOldScore() - newBest)));

        String filename = solution.getInstance().getName() + "_" + System.currentTimeMillis() + ".txt";
        try (var bw = Files.newBufferedWriter(Path.of(FOLDER, filename))){
            solutionIO.export(bw, solution);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
