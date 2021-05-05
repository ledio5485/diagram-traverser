package org.camunda.challenge.diagramtraverser.pathfinder;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.Optional;

public interface PathFinderStrategy<V, E> {

    StrategyName getName();

    Optional<GraphPath<V, E>> getGraphPath(Graph<V, E> graph, V source, V sink);
}
