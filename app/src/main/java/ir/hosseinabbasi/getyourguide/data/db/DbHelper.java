package ir.hosseinabbasi.getyourguide.data.db;

import java.util.List;

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface DbHelper {
    Observable<List<Review.dataz>> getAllQuestions();
    Observable<Boolean> isQuestionEmpty();
    Observable<Boolean> saveQuestion(Review.dataz question);
    Observable<Boolean> saveQuestionList(List<Review.dataz> questionList);
}
