package org.camunda.challenge.diagramtraverser.resultprinter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
class ConsoleDataOutput implements DataOutput {

    @Override
    public void write(List<String> nodes) {
        if (CollectionUtils.isEmpty(nodes) || nodes.size() < 2) {
            throw new RuntimeException("No path was found.");
        }
        log.info("The path from {} to {} is: {}", nodes.get(0), nodes.get(nodes.size() - 1), nodes.toString());
    }
}
