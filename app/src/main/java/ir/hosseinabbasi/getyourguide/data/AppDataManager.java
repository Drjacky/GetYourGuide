package ir.hosseinabbasi.getyourguide.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import ir.hosseinabbasi.getyourguide.data.db.DbHelper;
//import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;
import ir.hosseinabbasi.getyourguide.di.ApplicationContext;
import ir.hosseinabbasi.getyourguide.utils.AppConstants;
import ir.hosseinabbasi.getyourguide.utils.AppLogger;
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
    public Observable<Boolean> saveQuestion(Data question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Data> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }

    @Override
    public Observable<List<Data>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {


        //GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        //GsonBuilder builder = new GsonBuilder();
        //final Gson gson = builder.create();

        return mDbHelper.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            ReviewPOJO.class);
                            //AppLogger.d("ByMeGsontype",type.toString());
                            /*List<Review> questionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);*/
                            //List<ReviewPOJO> questionList = new ArrayList<ReviewPOJO>();
                            /*ReviewPOJO rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);*/

                            /*List<ReviewPOJO.Data> rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);*/

                            /*List<Data> rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);*/

                            JsonParser parser = new JsonParser();
                            JsonObject rootObejct = parser.parse(CommonUtils.loadJSONFromAsset(mContext,
                                    AppConstants.SEED_DATABASE_QUESTIONS)).getAsJsonObject();
                            JsonElement dataElement = rootObejct.get("data");

                            Gson gson = new Gson();
                            List<Data> dataList = new ArrayList<>();
                            //Check if "data" element is an array or an object and parse accordingly...
                            if (dataElement.isJsonObject()) {
                                //The returned list has only 1 element
                                Data d = gson.fromJson(dataElement, Data.class);
                                dataList.add(d);
                            }
                            else if (dataElement.isJsonArray()) {
                                //The returned list has >1 elements
                                Type dataListType = new TypeToken<List<Data>>() {}.getType();
                                dataList = gson.fromJson(dataElement, dataListType);
                            }


                            /*ReviewPOJO allData = new Gson().fromJson(CommonUtils.loadJSONFromAsset(mContext,
                                    AppConstants.SEED_DATABASE_QUESTIONS),type);
                            List<Data> rvd = allData.getData();*/

                            //AppLogger.d("ByMegson.fromJson",rv.toString());
                            /*for (ReviewPOJO.DataType data : rv.getData()) {
                                questionList.add(data);
                            }*/

                            return saveQuestionList(dataList);
                            //return saveQuestionList(questionList);
                            //return saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }
}
