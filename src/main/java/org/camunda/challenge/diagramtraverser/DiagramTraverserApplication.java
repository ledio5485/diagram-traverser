package org.camunda.challenge.diagramtraverser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DiagramTraverserApplication {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(DiagramTraverserApplication.class, args);
        log.info("APPLICATION FINISHED");
    }
}
