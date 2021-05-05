package org.camunda.challenge.diagramtraverser.core;

import lombok.extern.slf4j.Slf4j;
import org.camunda.challenge.diagramtraverser.core.model.Node;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class GraphFactory<N extends Node, E> {

    Graph<N, E> createDirectedGraph(N source, N sink) {
        log.info("Create a traversable data structure from the parsed BPMN model.");

        var graph = new DefaultDirectedGraph<N, E>((Class<? extends E>) Object.class);
        graph.addVertex(source);
        processAdjacentNodes(graph, source, sink);

        return graph;
    }

    private void processAdjacentNodes(Graph<N, E> graph, N source, N sink) {
        source.getAdjacentNodes().forEach(currentNode -> addCurrentNodeAndEdge(graph, source, sink, (N) currentNode));
    }

    private void addCurrentNodeAndEdge(Graph<N, E> graph, N source, N sink, N currentNode) {
        boolean vertexIsAdded = graph.addVertex(currentNode);
        graph.addEdge(source, currentNode);
        if (vertexIsAdded && !currentNode.equals(sink)) {
            processAdjacentNodes(graph, currentNode, sink);
        }
    }
}