package org.camunda.challenge.diagramtraverser.pathfinder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class PathFinderStrategyManager<V, E> {
    private final List<PathFinderStrategy<V, E>> pathFinderStrategies;

    public PathFinderStrategy<V, E> getStrategy(StrategyName strategyName) {
        return pathFinderStrategies.stream()
                .filter(s -> s.getName().equals(strategyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Strategy %s not found. Please choose one of these: %s", strategyName, Arrays.toString(StrategyName.values()))));
    }
}
