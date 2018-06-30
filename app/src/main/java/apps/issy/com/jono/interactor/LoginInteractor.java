package apps.issy.com.jono.interactor;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface LoginInteractor {

    interface onLoginFinished{
        void onValidationError();
        void onLoginFailed();
        void onSignUpFailed();
        void onLoginSuccess();
        void onSignUpSuccess();
    }

    void login(String email, String password, onLoginFinished listener);

    void signUp(String email, String password, String username, onLoginFinished listener);

}
