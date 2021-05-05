package org.camunda.challenge.diagramtraverser.pathfinder;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DijkstraPathFinder<V, E> implements PathFinderStrategy<V, E> {
    @Override
    public StrategyName getName() {
        return StrategyName.DIJKSTRA;
    }

    @Override
    public Optional<GraphPath<V, E>> getGraphPath(Graph<V, E> graph, V source, V sink) {
        var graphPath = new DijkstraShortestPath<>(graph).getPaths(source).getPath(sink);

        return graphPath == null ? Optional.empty() : Optional.of(graphPath);
    }
}
