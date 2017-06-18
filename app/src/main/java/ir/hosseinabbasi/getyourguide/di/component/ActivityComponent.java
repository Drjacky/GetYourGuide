package ir.hosseinabbasi.getyourguide.di.component;

import ir.hosseinabbasi.getyourguide.di.PerActivity;
import ir.hosseinabbasi.getyourguide.di.module.ActivityModule;
import ir.hosseinabbasi.getyourguide.ui.main.MainActivity;
import dagger.Component;
import ir.hosseinabbasi.getyourguide.ui.splash.SplashActivity;

/**
 * Created by Dr.jacky on 2017/05/19.
 *
 */

@PerActivity /*@PerActivity is a scope and is used to tell the Dagger that the Context and Activity provided by the ActivityModule will be instantiated each time an Activity is created. So, these objects persist till that activity lives and each activity has its own set of these.*/

/*ApplicationComponent is added to use the graph that has already been generated and do exists because the MvpApp class persists till the application is running.*/
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SplashActivity activity);
}
