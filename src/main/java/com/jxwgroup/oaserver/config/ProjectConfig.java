package com.jxwgroup.oaserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project-config")
public class ProjectConfig {

    private String weatherKey;

    private String aggregateKey;

    private String indexPath;

}
