package apps.issy.com.jono.view;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface LoginView {

    void showProgress();

    void hideProgress();

    void showCredentialsError();

    void showLoginFailure();

    void showLoginSuccess();

    void showSignUpFailure();

    void showSignUpSuccess();

    void navigateHome();

    void clearMessage();

}
