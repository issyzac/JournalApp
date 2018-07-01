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

package apps.issy.com.jono.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import apps.issy.com.jono.model.entities.JournalModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by issy on 28/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

@Dao
public interface JournalModelDao {

    @Query("select * from JournalModel order by updatedAt desc")
    LiveData<List<JournalModel>> getAllJounals();

    @Query("select * from JournalModel order by updatedAt desc")
    List<JournalModel> getAllJournalList();

    @Query("select * from JournalModel where journalId = :journalId")
    LiveData<JournalModel> getJournalById(long journalId);

    @Insert(onConflict = REPLACE)
    void addJournal(JournalModel journalModel);

    @Delete
    void deleteJournal(JournalModel journalModel);

    @Update
    void updateJournal(JournalModel journalModel);

}
