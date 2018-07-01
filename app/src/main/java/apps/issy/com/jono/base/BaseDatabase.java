/*
 * Copyright (C) 2018 Isaya Zachariah Mollel - issyzac
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package apps.issy.com.jono.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import apps.issy.com.jono.model.dao.JournalModelDao;
import apps.issy.com.jono.model.entities.JournalModel;

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

    public abstract JournalModelDao journalModelDao();

}
