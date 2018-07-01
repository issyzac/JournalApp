package apps.issy.com.jono.presenter;

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
