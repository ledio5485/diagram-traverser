package org.camunda.challenge.diagramtraverser.pathfinder;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;

@Component
class RandomPathFinder<V, E> implements PathFinderStrategy<V, E> {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public StrategyName getName() {
        return StrategyName.RANDOM;
    }

    @Override
    public Optional<GraphPath<V, E>> getGraphPath(Graph<V, E> graph, V source, V sink) {
        var allPaths = new AllDirectedPaths<>(graph).getAllPaths(source, sink, true, null);

        return allPaths.isEmpty() ? Optional.empty() : Optional.of(allPaths.get(secureRandom.nextInt(allPaths.size())));
    }
}
