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

public class LoginInteractorImpl implements Interactor.LoginInteractor {

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
    public void signUp(String email, String password, final onLoginFinished listener) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            listener.onValidationError();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            listener.onSignUpSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            listener.onSignUpFailed();
                        }
                    }
                });
        }

    }
}
