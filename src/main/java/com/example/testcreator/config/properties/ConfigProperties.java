package com.example.testcreator.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ConfigurationProperties("api")
public class ConfigProperties {
  private String uri;
}
