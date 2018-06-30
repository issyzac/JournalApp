package apps.issy.com.jono.interactor;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoginInteractorImpl implements LoginInteractor {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public void login(String email, String password, final onLoginFinished listener) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            listener.onValidationError();
        }else {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            listener.onLoginSuccess();
                        } else {
                            listener.onLoginFailed();
                        }

                    }
                });
        }

    }

    @Override
    public void signUp(String email, String password, String username, onLoginFinished listener) {
        /*
         * TODO Firebase signUp code goes here
         *
         * If validationError -> Call listener.onValidationError();
         * If signUpError -> Call listener.onSignUpFailed();
         * If loginSuccess -> Call listener.onSignUpSuccess();
         */

    }
}
