package ir.hosseinabbasi.getyourguide;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import javax.inject.Inject;

import ir.hosseinabbasi.getyourguide.BuildConfig;
import ir.hosseinabbasi.getyourguide.data.DataManager;
import ir.hosseinabbasi.getyourguide.di.component.ApplicationComponent;
import ir.hosseinabbasi.getyourguide.di.component.DaggerApplicationComponent;
import ir.hosseinabbasi.getyourguide.di.module.ApplicationModule;
import ir.hosseinabbasi.getyourguide.utils.AppLogger;


/**
 * Created by Dr.jacky on 2017/05/19.
 */

public class MvpApp extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
