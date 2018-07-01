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
package apps.issy.com.jono;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import apps.issy.com.jono.base.BaseActivity;
import apps.issy.com.jono.interactor.LoginInteractorImpl;
import apps.issy.com.jono.presenter.LoginPresenterImpl;
import apps.issy.com.jono.presenter.Presenter;
import apps.issy.com.jono.view.BaseView;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoginSignUpActivity extends BaseActivity implements BaseView.LoginView {

    private EditText mLoginEmailEditText, mLoginPasswordEditText;
    private EditText mSignUpEmailEditText, mSignUpPasswordEditText, mSignUpPasswordRepeatEditText, mSignUpUsernameEditText;
    private Button mLoginButton, mSignUpButton;
    private LinearLayout mLoginContainer, mSignupContainer;
    private RelativeLayout mProgressContainer, mSignUpProgressContainer;
    private TextView mMessagesTextView;

    private Presenter.LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();

        mPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());

    }

    private void setUpViews(){

        mLoginEmailEditText = findViewById(R.id.et_login_email);
        mLoginPasswordEditText = findViewById(R.id.et_login_password);
        mSignUpEmailEditText = findViewById(R.id.et_sign_up_email);
        mSignUpPasswordEditText = findViewById(R.id.et_sign_up_password);
        mSignUpPasswordRepeatEditText = findViewById(R.id.et_sign_up_password_repeat);

        mLoginButton = findViewById(R.id.button_login);
        mSignUpButton = findViewById(R.id.button_signup);

        mProgressContainer = findViewById(R.id.progress_container);
        mLoginContainer = findViewById(R.id.login_container);
        mSignupContainer = findViewById(R.id.sign_up_container);
        mSignUpProgressContainer = findViewById(R.id.sign_up_progress_container);

        mMessagesTextView = findViewById(R.id.tv_messages);
    }

    public void switchToSignUp(View view){
        mLoginContainer.setVisibility(View.GONE);
        mSignupContainer.setVisibility(View.VISIBLE);
    }

    public void switchToLogin(View view){
        mSignupContainer.setVisibility(View.GONE);
        mLoginContainer.setVisibility(View.VISIBLE);
    }

    public void loginUser(View view){
        mPresenter.validateCredentials(mLoginEmailEditText.getText().toString(), mLoginPasswordEditText.getText().toString());
    }

    public void signUpUser(View view){
        mPresenter.validateSignUpCredentials(mSignUpEmailEditText.getText().toString(), mSignUpPasswordEditText.getText().toString(), mSignUpPasswordRepeatEditText.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        mLoginButton.setVisibility(View.GONE);
        mProgressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoginButton.setVisibility(View.VISIBLE);
        mProgressContainer.setVisibility(View.GONE);
    }

    @Override
    public void showSignUpProgress() {
        mSignUpProgressContainer.setVisibility(View.VISIBLE);
        mSignUpButton.setVisibility(View.GONE);
    }

    @Override
    public void hideSignUpProgress() {
        mSignUpProgressContainer.setVisibility(View.GONE);
        mSignUpButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCredentialsError() {
        mMessagesTextView.setText("Error,Check the fields and try again");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_700));
    }

    @Override
    public void showLoginFailure() {
        mMessagesTextView.setText(getResources().getString(R.string.incorrect_email_or_password));
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_700));
    }

    @Override
    public void showLoginSuccess() {
        mMessagesTextView.setText(getResources().getString(R.string.login_success));
        mMessagesTextView.setTextColor(getResources().getColor(R.color.green_800));
    }

    @Override
    public void showSignUpFailure() {
        mMessagesTextView.setText("Error signing up, check fields and try again later");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_700));
    }

    @Override
    public void showSignUpSuccess() {
        mMessagesTextView.setText("User signed up successfully");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.green_800));
    }

    @Override
    public void clearMessage() {
        mMessagesTextView.setText("");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.card_grid_text));
    }

    @Override
    public void navigateHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
