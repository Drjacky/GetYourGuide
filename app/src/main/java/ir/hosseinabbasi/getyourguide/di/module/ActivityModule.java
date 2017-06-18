package ir.hosseinabbasi.getyourguide.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.hosseinabbasi.getyourguide.di.ActivityContext;
import ir.hosseinabbasi.getyourguide.di.PerActivity;
import ir.hosseinabbasi.getyourguide.ui.main.MainMvpPresenter;
import ir.hosseinabbasi.getyourguide.ui.main.MainMvpView;
import ir.hosseinabbasi.getyourguide.ui.main.MainPresenter;
import ir.hosseinabbasi.getyourguide.ui.splash.SplashMvpPresenter;
import ir.hosseinabbasi.getyourguide.ui.splash.SplashMvpView;
import ir.hosseinabbasi.getyourguide.ui.splash.SplashPresenter;
import ir.hosseinabbasi.getyourguide.utils.rx.AppSchedulerProvider;
import ir.hosseinabbasi.getyourguide.utils.rx.SchedulerProvider;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Module /*To provide the dependency for a class we have to create a Module class. This class defines the methods that provide the dependency.*/
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides /*The dependency provider method*/
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView>
                                                                     presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView>
                                                               presenter) {
        return presenter;
    }
}
