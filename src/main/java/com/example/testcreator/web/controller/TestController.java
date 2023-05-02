package com.example.testcreator.web.controller;

import com.example.testcreator.service.TestService;
import com.example.testcreator.web.dto.Test;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
  private TestService testService;

  @GetMapping("/all/{id}")
  public String getAllTests(Model model, @PathVariable String id){
    List<Test> res;
    try{
      res = testService.getAllTests(id);
    } catch (Throwable ex){
      model.addAttribute("message", ex.getMessage());
      return "error";
    }
    model.addAttribute("tests", res);
    return "main";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam(name = "deleteName") String name){

  }
}
