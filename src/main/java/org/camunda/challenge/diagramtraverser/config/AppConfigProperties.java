package org.camunda.challenge.diagramtraverser.config;

import lombok.Data;
import org.camunda.challenge.diagramtraverser.pathfinder.StrategyName;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Data
public class AppConfigProperties {
    private String xmlUrl;
    private StrategyName pathFinderStrategy;
}
