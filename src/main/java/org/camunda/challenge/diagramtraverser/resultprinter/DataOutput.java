package org.camunda.challenge.diagramtraverser.resultprinter;

import java.util.List;

public interface DataOutput {
    void write(List<String> nodes);
}
