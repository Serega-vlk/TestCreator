package com.example.testcreator.web.controller;

import com.example.testcreator.cache.SessionIdCacheService;
import com.example.testcreator.service.TestService;
import com.example.testcreator.web.dto.Marks;
import com.example.testcreator.web.dto.Test;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
  private TestService testService;
  private final SessionIdCacheService sessionIdCacheService;

  @GetMapping("/all")
  public String getAllTests(HttpServletRequest request,
                            Model model){
    List<Test> res;
    try{
      res = testService.getAllTests(sessionIdCacheService.get(request.getSession().getId()).code());
    } catch (Throwable ex){
      model.addAttribute("message", ex.getMessage());
      return "error";
    }
    model.addAttribute("tests", res);
    return "main";
  }

  @PostMapping("/delete")
  public String delete(HttpServletRequest request,
                       Model model,
                       @RequestParam(name = "deleteName") String name){
    try{
      testService.deleteTest(sessionIdCacheService.get(request.getSession().getId()).code(), name);
    } catch (Throwable ex){
      model.addAttribute("message", ex.getMessage());
      return "error";
    }
    return "redirect:/test/all";
  }

  @PostMapping("/marks")
  public String requestMarks(HttpServletRequest request,
                       Model model,
                       @RequestParam(name = "markTestName") String name){
    try{
      Marks marks = testService.getMarksByTest(sessionIdCacheService.get(request.getSession().getId()).code(), name);
      model.addAttribute("marks", marks);
    } catch (Throwable ex){
      model.addAttribute("message", ex.getMessage());
      return "error";
    }
    return "marks";
  }
}
