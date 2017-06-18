package ir.hosseinabbasi.getyourguide.data.db;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.model.DaoMaster;
import ir.hosseinabbasi.getyourguide.data.db.model.DaoSession;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;


/**
 * Created by Dr.jacky on 2017/05/19.
 * AppDbHelper class will be used by DataManager to access the SQLite database.
 */

@Singleton /*@Singleton ensure a single instance of a class globally.*/
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Data>> getAllReviews() {
        return Observable.fromCallable(new Callable<List<Data>>() {
            @Override
            public List<Data> call() throws Exception {
                return mDaoSession.getDataDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isReviewEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getDataDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveReview(final Data review) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getDataDao().insert(review);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveReviewList(final List<Data> reviewList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getDataDao().insertInTx(reviewList);
                return true;
            }
        });
    }
}
