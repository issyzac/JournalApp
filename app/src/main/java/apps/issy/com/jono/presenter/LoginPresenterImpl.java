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
package apps.issy.com.jono.presenter;

import android.text.TextUtils;

import android.os.Handler;

import apps.issy.com.jono.interactor.Interactor;
import apps.issy.com.jono.interactor.LoginInteractorImpl;
import apps.issy.com.jono.view.BaseView;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoginPresenterImpl implements Presenter.LoginPresenter, LoginInteractorImpl.onLoginFinished {

    private BaseView.LoginView loginView;
    private Interactor.LoginInteractor loginInteractor;

    public LoginPresenterImpl(BaseView.LoginView view, Interactor.LoginInteractor interactor){
        this.loginView = view;
        this.loginInteractor = interactor;
    }

    @Override
    public void validateSignUpCredentials(String email, String password, String repeatedPassword) {
        if (loginView != null){
            loginView.showSignUpProgress();
        }
        loginInteractor.signUp(email, password, this);
    }

    @Override
    public void validateCredentials(String email, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(email, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onValidationError() {
        if (loginView != null){
            loginView.showCredentialsError();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    loginView.clearMessage();
                }
            }, 2000);
            loginView.hideProgress();
        }
    }

    @Override
    public void onLoginFailed() {
        if (loginView != null){
            loginView.showLoginFailure();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    loginView.clearMessage();
                }
            }, 2000);
            loginView.hideProgress();
        }
    }

    @Override
    public void onLoginSuccess() {
        if (loginView != null){
            loginView.showLoginSuccess();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    loginView.navigateHome();
                }
            }, 1000);

        }
    }

    @Override
    public void onSignUpFailed() {
        if (loginView != null){
            loginView.showSignUpFailure();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    loginView.clearMessage();
                }
            }, 2000);
            loginView.hideSignUpProgress();
        }
    }

    @Override
    public void onSignUpSuccess() {
        if (loginView != null){
            loginView.showSignUpSuccess();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    loginView.navigateHome();
                }
            }, 1000);
        }
    }
}
