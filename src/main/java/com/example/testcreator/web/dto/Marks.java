package com.example.testcreator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marks {
  private String testName;
  private Integer total;
  private List<Mark> marks;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Mark {
    String name;
    String surname;
    String group;
    Integer mark;
  }
}
