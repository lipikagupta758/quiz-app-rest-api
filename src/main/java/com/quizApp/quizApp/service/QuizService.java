package com.quizApp.quizApp.service;

import com.quizApp.quizApp.dao.QuestionDao;
import com.quizApp.quizApp.dao.QuizDao;
import com.quizApp.quizApp.model.Question;
import com.quizApp.quizApp.model.QuestionWrapper;
import com.quizApp.quizApp.model.Quiz;
import com.quizApp.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions= questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizid) {
        Optional<Quiz> quiz= quizDao.findById(quizid);
        List<Question> questions= quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser= new ArrayList<>();
        for(Question q: questions){
            QuestionWrapper qw= new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizid, List<Response> response) {
        int score=0;
        Optional<Quiz> quiz= quizDao.findById(quizid);
        List<Question> questions= quiz.get().getQuestions();
        for(Question q: questions){
            for (Response r: response){
                if(q.getId()== r.getId() && q.getRightAnswer().equals(r.getResponse())) score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);

    }
}
