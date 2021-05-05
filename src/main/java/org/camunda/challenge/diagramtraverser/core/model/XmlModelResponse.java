package org.camunda.challenge.diagramtraverser.core.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class XmlModelResponse {
    private String id;

    @JsonSetter("bpmn20Xml")
    private String xml;
}
