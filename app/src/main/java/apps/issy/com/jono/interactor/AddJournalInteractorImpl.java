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

package apps.issy.com.jono.interactor;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.Random;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class AddJournalInteractorImpl implements Interactor.AddJournalInteractor {

    @SuppressLint("StaticFieldLeak")
    @Override
    public void addJournal(final String title, final String content, final onJournalAdded listener, final BaseDatabase database) {

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)){
            listener.validationError();
        }

        new AsyncTask<Void, Void, Void>(){
            BaseDatabase mDatabase = database;
            @Override
            protected Void doInBackground(Void... voids) {
                JournalModel journalModel = new JournalModel();

                long range = 1234567L;
                Random r = new Random();
                long randomId = (long)(r.nextDouble()*range);

                journalModel.setJournalId(randomId);
                journalModel.setTitle(title);
                journalModel.setJournalContents(content);
                journalModel.setCreatedAt(Calendar.getInstance().getTimeInMillis());
                journalModel.setUpdatedAt(Calendar.getInstance().getTimeInMillis());

                mDatabase.journalModelDao().addJournal(journalModel);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.addedSuccessfully();
            }
        }.execute();
    }
}
