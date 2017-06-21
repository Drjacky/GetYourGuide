package ir.hosseinabbasi.getyourguide.data;

/**
 * Created by Dr.jacky on 2017/05/19.
 * It is an interface that is implemented by the AppDataManager. It contains methods, exposed for all the data handling operations. Ideally, it delegates the services provided by all the Helper classes.
 * So DataManager interface extends DbHelper and ApiHelper interfaces.
 */

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.DbHelper;

public interface DataManager extends DbHelper /*, ApiHelper*/ {
    Observable<Boolean> seedDatabaseReviews();
}
