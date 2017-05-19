package ir.hosseinabbasi.getyourguide.data;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

import io.reactivex.Observable;
import ir.hosseinabbasi.getyourguide.data.db.DbHelper;

public interface DataManager extends DbHelper {
    Observable<Boolean> seedDatabaseQuestions();
}
