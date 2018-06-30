package apps.issy.com.jono.view;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface AddJournalView {

    void showProgress();

    void hideProgress();

    void showAddingJournalSuccess();

    void showAddingJournalFailed();

    void showValidationError();

    void dismissDialogue();

    void clearMesages();

}
