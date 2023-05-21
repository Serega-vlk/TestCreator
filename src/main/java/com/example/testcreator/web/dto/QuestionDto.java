package com.example.testcreator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {
  private String text;
  private Integer value;
  private String text1;
  private String isCorrect1;
  private String text2;
  private String isCorrect2;
  private String text3;
  private String isCorrect3;
  private String text4;
  private String isCorrect4;
  private String text5;
  private String isCorrect5;
}
