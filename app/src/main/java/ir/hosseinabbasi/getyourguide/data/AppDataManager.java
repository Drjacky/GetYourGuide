package ir.hosseinabbasi.getyourguide.data;

import android.content.Context;

import com.google.gson.Gson;
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
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;
import ir.hosseinabbasi.getyourguide.di.ApplicationContext;
import ir.hosseinabbasi.getyourguide.utils.AppConstants;
import ir.hosseinabbasi.getyourguide.utils.CommonUtils;

/**
 * Created by Dr.jacky on 2017/05/19.
 * AppDataManager class will provide access to the data of the application.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;

    @Inject /*@Inject on the constructor instructs the Dagger to accumulate all the parameter dependencies when the class is being constructed.
    @ApplicationContext Qualifier facilitates AppDataManager to get the context object of the application from dagger’s dependency graph.
    */
    /*Or @Named("application_context")*/ /* $$$ */
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper) {
        mContext = context;
        mDbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> isReviewEmpty() {
        return mDbHelper.isReviewEmpty();
    }

    @Override
    public Observable<Boolean> saveReview(Data review) {
        return mDbHelper.saveReview(review);
    }

    @Override
    public Observable<Boolean> saveReviewList(List<Data> reviewList) {
        return mDbHelper.saveReviewList(reviewList);
    }

    @Override
    public Observable<List<Data>> getAllReviews() {
        return mDbHelper.getAllReviews();
    }

    @Override
    public Observable<Boolean> seedDatabaseReviews() {


        //GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        //GsonBuilder builder = new GsonBuilder();
        //final Gson gson = builder.create();

        return mDbHelper.isReviewEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            ReviewPOJO.class);
                            /*List<Review> reviewList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_REVIEWS),
                                    type);*/
                            //List<ReviewPOJO> reviewList = new ArrayList<ReviewPOJO>();
                            /*ReviewPOJO rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_REVIEWS),
                                    type);*/

                            /*List<ReviewPOJO.Data> rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_REVIEWS),
                                    type);*/

                            /*List<Data> rv = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_REVIEWS),
                                    type);*/

                            JsonParser parser = new JsonParser();
                            JsonObject rootObject = parser.parse(CommonUtils.loadJSONFromAsset(mContext,
                                    AppConstants.SEED_DATABASE_REVIEWS)).getAsJsonObject();
                            JsonElement dataElement = rootObject.get("data");

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
                                    AppConstants.SEED_DATABASE_REVIEWS),type);
                            List<Data> rvd = allData.getData();*/

                            /*for (ReviewPOJO.DataType data : rv.getData()) {
                                reviewList.add(data);
                            }*/

                            return saveReviewList(dataList);
                            //return saveReviewList(reviewList);
                            //return saveReviewList(reviewList);
                        }
                        return Observable.just(false);
                    }
                });
    }
}
