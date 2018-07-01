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
package apps.issy.com.jono.view;

import java.util.List;

import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface BaseView {

    public interface MainView {

        void showProgress();

        void hideProgress();

        void setItems(List<JournalModel> items);

        void showMessage(String message);

        void viewDetails(long journalId);

    }

    public interface LoginView {

        void showProgress();

        void hideProgress();

        void showSignUpProgress();

        void hideSignUpProgress();

        void showCredentialsError();

        void showLoginFailure();

        void showLoginSuccess();

        void showSignUpFailure();

        void showSignUpSuccess();

        void navigateHome();

        void clearMessage();

    }

    public interface JournalDetailsView {

        void showProgress();

        void hideProgress();

        void setBinder(JournalModel journalModel);

        void showEditedSuccessfully();

        void showDeleted();

        void navigateBack();

    }

    public interface AddJournalView {

        void showProgress();

        void hideProgress();

        void showAddingJournalSuccess();

        void showAddingJournalFailed();

        void showValidationError();

        void dismissDialogue();

        void clearMesages();

    }

}
