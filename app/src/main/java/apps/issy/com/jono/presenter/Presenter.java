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
package apps.issy.com.jono.presenter;

import android.content.Context;

import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface Presenter {

    public interface MainPresenter {

        void onResume();

        void onItemClicked(JournalModel journalModel);

        void scheduleJob(Context context);

        void onDestroy();

    }

    public interface LoginPresenter {

        void validateSignUpCredentials(String email, String password, String repeatedPassword);

        void validateCredentials(String email, String password);

        void onDestroy();

    }

    public interface AddJournalPresenter {

        void validateJournalContents(String journalTitle, String journalContent);

        void onDestroy();

    }

    public interface JournalDetailsPresenter {

        void loadJournalWithId(long journaId);

        void saveEdited(String title, String contents);

        void dismissDialog();

        void deleteJournal();

        void onDestroy();

    }

}
