package ir.hosseinabbasi.getyourguide.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Dr.jacky on 2017/05/19.
 *
 */

@Qualifier /*DatabaseInfo is used to provide the database name in the class dependency. Since a String class in being provided as a dependency, it always a good idea to qualify it so that the Dagger can explicitly resolve it.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {
}
