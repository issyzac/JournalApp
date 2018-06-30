package apps.issy.com.jono.presenter;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface LoginPresenter {

    void validateSignUpCredentials(String email, String password, String repeatedPassword);

    void validateCredentials(String email, String password);

    void onDestroy();

}
