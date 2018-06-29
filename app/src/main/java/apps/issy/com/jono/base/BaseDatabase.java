package apps.issy.com.jono.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import apps.issy.com.jono.model.data.JournalModel;

/**
 * Created by issy on 28/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

@Database(entities = {JournalModel.class}, version = 1)
public abstract class BaseDatabase extends RoomDatabase{

    private static BaseDatabase INSTANCE;

    public static BaseDatabase getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BaseDatabase.class, "jono_db").build();
        }
        return INSTANCE;
    }

}
