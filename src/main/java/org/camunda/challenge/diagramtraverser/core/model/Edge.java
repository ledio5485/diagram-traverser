package org.camunda.challenge.diagramtraverser.core.model;

import lombok.Data;

@Data
public class Edge<T> {
    private T source;
    private T target;
}
