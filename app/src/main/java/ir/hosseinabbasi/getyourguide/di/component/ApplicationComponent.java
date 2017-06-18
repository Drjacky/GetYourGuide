package ir.hosseinabbasi.getyourguide.di.component;

/**
 * Created by Dr.jacky on 2017/05/19.
 * Links the MvpApp dependency and the ApplicationModule.
 * This class also provides methods that are used to access the dependencies that exist in the dependency graph.
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

    /*When the dependencies are provided through field injection i.e. @inject on the member variables, we have to tell the Dagger to scan this class through the implementation of this interface.*/
    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
