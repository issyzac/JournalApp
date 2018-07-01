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
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import apps.issy.com.jono.MainActivity;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoadJournalsInteractorImpl implements Interactor.LoadJournalsInteractor {

    LiveData<List<JournalModel>> items;

    @Override
    public void loadJournalItems(final onFinishListener listener, final BaseDatabase database) {
        //Load all the jornals of this user from the database

        items = database.journalModelDao().getAllJounals();
        items.observeForever(new android.arch.lifecycle.Observer<List<JournalModel>>() {
            @Override
            public void onChanged(@Nullable List<JournalModel> journalModels) {
                listener.onFinished(journalModels);
            }
        });
    }

    @Override
    public void removeObserver() {
    }
}
