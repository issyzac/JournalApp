package apps.issy.com.jono.presenter;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface AddJournalPresenter {

    void validateJournalContents(String journalTitle, String journalContent);

    void onDestroy();

}
