package ir.hosseinabbasi.getyourguide.data.db;

import java.util.List;

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface DbHelper {
    Observable<List<Data>> getAllReviews();
    Observable<Boolean> isReviewEmpty();
    Observable<Boolean> saveReview(Data review);
    Observable<Boolean> saveReviewList(List<Data> reviewList);
}
