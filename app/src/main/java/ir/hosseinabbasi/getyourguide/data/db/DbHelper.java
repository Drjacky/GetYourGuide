package ir.hosseinabbasi.getyourguide.data.db;

import java.util.List;

import io.reactivex.Observable;
//import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface DbHelper {
    Observable<List<Data>> getAllQuestions();
    Observable<Boolean> isQuestionEmpty();
    Observable<Boolean> saveQuestion(Data question);
    Observable<Boolean> saveQuestionList(List<Data> questionList);
}
