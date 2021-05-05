package org.camunda.challenge.diagramtraverser.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@EqualsAndHashCode
public class FlowNodeWrapper implements Node {
    private final FlowNode flowNode;

    @Override
    public String getId() {
        return flowNode.getId();
    }

    @Override
    public Collection<Node> getAdjacentNodes() {
        return flowNode.getOutgoing().stream()
                .map(sequenceFlow -> new FlowNodeWrapper(sequenceFlow.getTarget()))
                .collect(Collectors.toList());
    }
}
