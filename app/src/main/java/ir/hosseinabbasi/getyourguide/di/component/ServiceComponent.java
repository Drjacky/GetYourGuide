package ir.hosseinabbasi.getyourguide.di.component;

import dagger.Component;
import ir.hosseinabbasi.getyourguide.di.PerService;
import ir.hosseinabbasi.getyourguide.di.module.ServiceModule;
import ir.hosseinabbasi.getyourguide.service.SyncService;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
