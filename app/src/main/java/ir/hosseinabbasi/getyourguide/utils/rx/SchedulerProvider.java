package ir.hosseinabbasi.getyourguide.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}
