package ir.hosseinabbasi.getyourguide.data.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import ir.hosseinabbasi.getyourguide.data.db.model.DaoMaster;
import ir.hosseinabbasi.getyourguide.di.ApplicationContext;
import ir.hosseinabbasi.getyourguide.di.DatabaseInfo;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject /*@DatabaseInfo qualifier helps the dagger to distinguish between String and Integer Dependencies from existing same types in the dependency graph.*/
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
