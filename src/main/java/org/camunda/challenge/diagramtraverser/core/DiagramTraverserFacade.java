package org.camunda.challenge.diagramtraverser.core;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface DiagramTraverserFacade {

    List<String> getVertexList(String source, String sink) throws JsonProcessingException, Exception;
}
