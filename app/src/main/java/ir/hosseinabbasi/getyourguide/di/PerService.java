package ir.hosseinabbasi.getyourguide.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}

