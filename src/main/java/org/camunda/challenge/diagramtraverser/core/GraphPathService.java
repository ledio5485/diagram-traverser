package org.camunda.challenge.diagramtraverser.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.challenge.diagramtraverser.core.model.Node;
import org.camunda.challenge.diagramtraverser.pathfinder.PathFinderStrategyManager;
import org.camunda.challenge.diagramtraverser.pathfinder.StrategyName;
import org.jgrapht.GraphPath;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GraphPathService<N extends Node, E> {

    private final PathFinderStrategyManager<N, E> pathFinderStrategyManager;
    private final GraphFactory<N, E> graphFactory;

    public GraphPath<N, E> findSimplePath(N source, N sink, StrategyName strategyName) {

        var strategy = pathFinderStrategyManager.getStrategy(strategyName);
        var directedGraph = graphFactory.createDirectedGraph(source, sink);

        log.info("Finding a GraphPath from {} to {} using {} algorithm.", source.getId(), sink.getId(), strategy.getName());
        return strategy.getGraphPath(directedGraph, source, sink)
                .orElseThrow(() -> new RuntimeException("No path was found."));
    }
}
