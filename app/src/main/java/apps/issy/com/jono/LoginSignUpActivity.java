package apps.issy.com.jono;

import android.content.Intent;
import android.graphics.Color;
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
import apps.issy.com.jono.presenter.LoginPresenter;
import apps.issy.com.jono.presenter.LoginPresenterImpl;
import apps.issy.com.jono.view.LoginView;

/**
 * Created by issy on 29/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoginSignUpActivity extends BaseActivity implements LoginView {

    private EditText mLoginEmailEditText, mLoginPasswordEditText;
    private EditText mSignUpEmailEditText, mSignUpPasswordEditText, mSignUpPasswordRepeatEditText, mSignUpUsernameEditText;
    private Button mLoginButton, mSignUpButton;
    private LinearLayout mLoginContainer, mSignupContainer;
    private RelativeLayout mProgressContainer;
    private TextView mMessagesTextView;

    private LoginPresenter mPresenter;

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
        mSignUpUsernameEditText = findViewById(R.id.et_sign_up_username);

        mLoginButton = findViewById(R.id.button_login);
        mSignUpButton = findViewById(R.id.button_signup);

        mProgressContainer = findViewById(R.id.progress_container);
        mLoginContainer = findViewById(R.id.login_container);
        mSignupContainer = findViewById(R.id.sign_up_container);

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
    public void showCredentialsError() {
        mMessagesTextView.setText("Error,Check the fields and try again");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_700));
    }

    @Override
    public void showLoginFailure() {
        mMessagesTextView.setText("Incorrect email or password");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_700));
    }

    @Override
    public void showLoginSuccess() {
        mMessagesTextView.setText("Successfully logged in");
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