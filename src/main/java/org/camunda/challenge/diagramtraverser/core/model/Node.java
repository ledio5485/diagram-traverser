package org.camunda.challenge.diagramtraverser.core.model;

import java.util.Collection;

public interface Node {
    String getId();

    Collection<Node> getAdjacentNodes();
}
