package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.HashCode.model.HashCodeInstance;
import es.urjc.etsii.grafo.HashCode.model.HashCodeSolution;
import es.urjc.etsii.grafo.io.serializers.SolutionSerializer;

import java.io.BufferedWriter;
import java.io.IOException;

public class HCSolutionIO extends SolutionSerializer<HashCodeSolution, HashCodeInstance> {

    public HCSolutionIO(HCSolutionIOConfig config) {
        super(config);
    }

    @Override
    public void export(BufferedWriter bw, HashCodeSolution hashCodeSolution) throws IOException {
        throw new UnsupportedOperationException("HCSolutionIO::export, Solution export not impleented yet");
    }
}
