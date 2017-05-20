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
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Data>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Data>>() {
            @Override
            public List<Data> call() throws Exception {
                return mDaoSession.getDataDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getDataDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestion(final Data question) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getDataDao().insert(question);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Data> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getDataDao().insertInTx(questionList);
                return true;
            }
        });
    }
}
