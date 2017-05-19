package ir.hosseinabbasi.getyourguide.data.db;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.data.db.model.Review.dataz;
import ir.hosseinabbasi.getyourguide.data.db.model.DaoMaster;
import ir.hosseinabbasi.getyourguide.data.db.model.DaoSession;


/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Review.dataz>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Review.dataz>>() {
            @Override
            public List<Review.dataz> call() throws Exception {
                return mDaoSession.getReviewDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getReviewDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestion(final Review.dataz question) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getReviewDao().insert(question);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Review.dataz> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getReviewDao().insertInTx(questionList);
                return true;
            }
        });
    }
}
