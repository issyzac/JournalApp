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

import java.util.List;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface Interactor {

    public interface AddJournalInteractor {

        interface onJournalAdded{
            void addedSuccessfully();
            void failed();
            void validationError();
        }

        void addJournal(String title, String content, onJournalAdded listener, BaseDatabase database);

    }

    public interface JournalDetailInteractor {

        interface onJournalLoaded{
            void onLoaded(JournalModel journalModel);
            void showEditedSuccessfully();
            void deleted();
        }

        void loadJournalWithId(long journalId, BaseDatabase database, onJournalLoaded listener);

        void saveEditedJournal(JournalModel journalModel, BaseDatabase database, onJournalLoaded listener);

        void deleteJournal(JournalModel journalModel, BaseDatabase database, onJournalLoaded listener);

    }

    public interface LoadJournalsInteractor {

        interface onFinishListener {
            void onFinished(List<JournalModel> items);
        }

        void loadJournalItems(onFinishListener listener, BaseDatabase database);

        void removeObserver();

    }

    public interface LoginInteractor {

        interface onLoginFinished{
            void onValidationError();
            void onLoginFailed();
            void onSignUpFailed();
            void onLoginSuccess();
            void onSignUpSuccess();
        }

        void login(String email, String password, onLoginFinished listener);

        void signUp(String email, String password, onLoginFinished listener);

    }

}
