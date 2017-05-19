package ir.hosseinabbasi.getyourguide.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import ir.hosseinabbasi.getyourguide.data.db.DbHelper;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.di.ApplicationContext;
import ir.hosseinabbasi.getyourguide.utils.AppConstants;
import ir.hosseinabbasi.getyourguide.utils.CommonUtils;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper) {
        mContext = context;
        mDbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return mDbHelper.isQuestionEmpty();
    }

    @Override
    public Observable<Boolean> saveQuestion(Review.data question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Review.data> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }

    @Override
    public Observable<List<Review.data>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDbHelper.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            Review.class);
                            /*List<Review> questionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);*/
                            List<Review.data> questionList = new ArrayList<Review.data>();
                            Review rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);
                            for (Review.data data : rv.getDatas()) {
                                questionList.add(data);
                            }

                            return saveQuestionList(questionList);
                            //return saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }
}
