package com.example.testcreator.service;

import com.example.testcreator.web.dto.Marks;
import com.example.testcreator.web.dto.QuestionDto;
import com.example.testcreator.web.dto.Test;
import com.example.testcreator.web.dto.TestInput;

import java.util.List;

public interface TestService {
  List<Test> getAllTests(String id);

  void deleteTest(String id, String test);

  Marks getMarksByTest(String id, String test);

  void createTest(String id, Test test);
  void addQuestion(String id, QuestionDto question);
  void register(String id);
}
