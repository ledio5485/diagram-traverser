package org.camunda.challenge.diagramtraverser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.challenge.diagramtraverser.config.AppConfigProperties;
import org.camunda.challenge.diagramtraverser.core.DiagramTraverserFacade;
import org.camunda.challenge.diagramtraverser.resultprinter.DataOutput;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
@Slf4j
public class DiagramTraverserCLIRunner implements CommandLineRunner {
    private final DiagramTraverserFacade diagramTraverserFacade;
    private final DataOutput dataOutput;

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner with args: {}", Arrays.toString(args));

        validateInput(args);

        try {
            var vertexList = diagramTraverserFacade.getVertexList(args[0], args[1]);
            dataOutput.write(vertexList);
        } catch (Throwable error) {
            log.error(error.getMessage(), error);
            System.exit(-1);
        }
    }

    private void validateInput(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The application expects 2 input args: the start and the end node.");
        }
        if (args[0].equalsIgnoreCase(args[1])) {
            throw new IllegalArgumentException("The start and the end node are the same.");
        }
    }
}
