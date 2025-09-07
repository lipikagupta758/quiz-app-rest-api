package com.quizApp.quizApp.dao;

import com.quizApp.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    //Optional- @Query("SELECT q FROM Question q WHERE q.category = ?1"), because JPA will automatically implement this method based on its name
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    //native query means it is sql query, we used it because jpql does not supports orderby and limit keyword
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
