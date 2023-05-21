package com.example.testcreator.service.impl;

import com.example.testcreator.cache.TestRegistrationCacheService;
import com.example.testcreator.cache.records.TestRegistrationRecord;
import com.example.testcreator.config.properties.ConfigProperties;
import com.example.testcreator.service.TestService;
import com.example.testcreator.web.dto.Marks;
import com.example.testcreator.web.dto.QuestionDto;
import com.example.testcreator.web.dto.Test;
import com.example.testcreator.web.dto.TestInput;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
  private RestTemplate restTemplate;
  private ConfigProperties configProperties;
  private TestRegistrationCacheService testRegistrationCacheService;

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

  @Override
  public void deleteTest(String id, String test) {
    RequestEntity.BodyBuilder builder = RequestEntity.method(HttpMethod.DELETE, configProperties.getUri() + "/test");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("id", id);
    builder.headers(HttpHeaders.readOnlyHttpHeaders(map));
    restTemplate.exchange(builder.body(test.getBytes(StandardCharsets.UTF_8)), String.class);
  }

  @Override
  public Marks getMarksByTest(String id, String test) {
    RequestEntity.BodyBuilder builder = RequestEntity.method(HttpMethod.GET, configProperties.getUri() + "/test/" + test);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("id", id);
    builder.headers(HttpHeaders.readOnlyHttpHeaders(map));
    ParameterizedTypeReference<Marks> returnType = new ParameterizedTypeReference<>() {
    };
    return restTemplate.exchange(builder.build(), returnType).getBody();
  }

  @Override
  public void createTest(String id, Test test) {
    TestInput testInput = TestInput.builder()
        .name(test.getName())
        .total(test.getTotal())
        .questions(new ArrayList<>())
        .build();
    testRegistrationCacheService.createOrUpdate(id, new TestRegistrationRecord(testInput));
  }

  @Override
  public void addQuestion(String id, QuestionDto question) {
    TestInput testInput = testRegistrationCacheService.get(id).testInput();
    List<TestInput.Answer> answers = Stream.of(
        TestInput.Answer.builder()
            .text(question.getText1())
            .isCorrect(convertIsCorrect(question.getIsCorrect1()))
            .build(),
        TestInput.Answer.builder()
            .text(question.getText2())
            .isCorrect(convertIsCorrect(question.getIsCorrect2()))
            .build(),
        TestInput.Answer.builder()
            .text(question.getText3())
            .isCorrect(convertIsCorrect(question.getIsCorrect3()))
            .build(),
        TestInput.Answer.builder()
            .text(question.getText4())
            .isCorrect(convertIsCorrect(question.getIsCorrect4()))
            .build(),
        TestInput.Answer.builder()
            .text(question.getText5())
            .isCorrect(convertIsCorrect(question.getIsCorrect5()))
            .build()
    ).filter(answer -> !answer.getText().isEmpty()).toList();
    testInput.getQuestions().add(TestInput.QuestionNode.builder()
            .question(TestInput.Question.builder()
                .text(question.getText())
                .value(question.getValue())
                .build())
            .answers(answers)
        .build());
    testRegistrationCacheService.createOrUpdate(id, new TestRegistrationRecord(testInput));
  }

  @Override
  public void register(String id) {
    TestInput testInput = testRegistrationCacheService.get(id).testInput();
    RequestEntity.BodyBuilder builder = RequestEntity.method(HttpMethod.POST, configProperties.getUri() + "/test");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("id", id);
    builder.headers(HttpHeaders.readOnlyHttpHeaders(map));
    restTemplate.exchange(builder.body(testInput), String.class);
  }

  private boolean convertIsCorrect(String isCorrect){
    return isCorrect.equals("+");
  }
}
