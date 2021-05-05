package org.camunda.challenge.diagramtraverser.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.challenge.diagramtraverser.config.AppConfigProperties;
import org.camunda.challenge.diagramtraverser.core.model.Edge;
import org.camunda.challenge.diagramtraverser.core.model.FlowNodeWrapper;
import org.camunda.challenge.diagramtraverser.core.model.Node;
import org.camunda.challenge.diagramtraverser.core.model.XmlModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
class DiagramTraverserService implements DiagramTraverserFacade {

    private final WebClient webClient;
    private final GraphPathService<Node, Edge<Node>> graphPathService;
    private final AppConfigProperties config;

    @Override
    public List<String> getVertexList(String source, String sink) throws Exception {
        log.info("Reading BPMN model");

        var xmlModel = getXmlModel();
        var bpmnModelInstance = Bpmn.readModelFromStream(xmlModel);

        FlowNode sourceNode = bpmnModelInstance.getModelElementById(source);
        Objects.requireNonNull(sourceNode, "Cannot find a path for non existent node " + source);
        FlowNode sinkNode = bpmnModelInstance.getModelElementById(sink);
        Objects.requireNonNull(sinkNode, "Cannot find a path for non existent node " + sink);

        return graphPathService.findSimplePath(new FlowNodeWrapper(sourceNode), new FlowNodeWrapper(sinkNode), config.getPathFinderStrategy())
                .getVertexList()
                .stream()
                .map(Node::getId)
                .collect(Collectors.toList());
    }

    private InputStream getXmlModel() {
        var entityResponse = webClient
                .get()
                .uri(config.getXmlUrl())
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new RuntimeException("Could not get xml.")))
                .bodyToMono(XmlModelResponse.class)
                .block();

        return new ByteArrayInputStream(entityResponse.getXml().getBytes());
    }
}
