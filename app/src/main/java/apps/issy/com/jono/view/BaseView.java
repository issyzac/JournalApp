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
