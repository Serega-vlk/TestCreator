package com.example.testcreator.service.impl;

import com.example.testcreator.config.properties.ConfigProperties;
import com.example.testcreator.service.TestService;
import com.example.testcreator.web.dto.Test;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
  private RestTemplate restTemplate;
  private ConfigProperties configProperties;

  @Override
  public List<Test> getAllTests(String id) {
    RequestEntity.BodyBuilder builder = RequestEntity.method(HttpMethod.GET, configProperties.getUri() + "/test/all");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("id", id);
    builder.headers(HttpHeaders.readOnlyHttpHeaders(map));
    ParameterizedTypeReference<List<Test>> returnType = new ParameterizedTypeReference<>() {
    };
    return restTemplate.exchange(builder.build(), returnType).getBody();
  }
}
