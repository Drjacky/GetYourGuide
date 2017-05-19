package ir.hosseinabbasi.getyourguide.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
