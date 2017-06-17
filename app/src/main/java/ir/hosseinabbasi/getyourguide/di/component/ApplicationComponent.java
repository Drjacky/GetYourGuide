package ir.hosseinabbasi.getyourguide.di.component;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import ir.hosseinabbasi.getyourguide.MvpApp;
import ir.hosseinabbasi.getyourguide.data.DataManager;
import ir.hosseinabbasi.getyourguide.di.ApplicationContext;
import ir.hosseinabbasi.getyourguide.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
