package ir.hosseinabbasi.getyourguide.di.component;

import ir.hosseinabbasi.getyourguide.di.PerActivity;
import ir.hosseinabbasi.getyourguide.di.module.ActivityModule;
import ir.hosseinabbasi.getyourguide.ui.main.MainActivity;
import dagger.Component;
import ir.hosseinabbasi.getyourguide.ui.splash.SplashActivity;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SplashActivity activity);
}
