package ir.hosseinabbasi.getyourguide.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {
}
