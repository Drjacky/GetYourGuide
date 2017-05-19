package ir.hosseinabbasi.getyourguide.di;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
