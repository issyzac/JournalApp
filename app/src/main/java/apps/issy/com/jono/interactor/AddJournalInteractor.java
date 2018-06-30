package apps.issy.com.jono.interactor;

import apps.issy.com.jono.base.BaseDatabase;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface AddJournalInteractor {

    interface onJournalAdded{
        void addedSuccessfully();
        void failed();
        void validationError();
    }

    void addJournal(String title, String content, onJournalAdded listener, BaseDatabase database);

}
