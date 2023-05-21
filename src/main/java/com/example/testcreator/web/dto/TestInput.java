package com.example.testcreator.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestInput {
  private String name;
  private Integer total;
  private List<QuestionNode> questions;

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class QuestionNode{
    private Question question;
    private List<Answer> answers;

  }

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Answer{
    private String text;

    @JsonProperty("isCorrect")
    private boolean isCorrect;
  }

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Question{
    private String text;
    private Integer value;
  }
}
