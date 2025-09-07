package com.quizApp.quizApp.controller;

import com.quizApp.quizApp.model.Question;
import com.quizApp.quizApp.model.QuestionWrapper;
import com.quizApp.quizApp.model.Quiz;
import com.quizApp.quizApp.model.Response;
import com.quizApp.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{quizid}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizid){
        return quizService.getQuizQuestions(quizid);
    }

    @PostMapping("submit/{quizid}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizid, @RequestBody List<Response> response){
        System.out.println("Your quiz score is: ");
        return quizService.calculateResult(quizid, response);

    }

}
