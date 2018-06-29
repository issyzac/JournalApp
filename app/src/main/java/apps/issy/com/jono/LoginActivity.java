package apps.issy.com.jono;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import apps.issy.com.jono.base.BaseActivity;

/**
 * Created by issy on 28/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mSignupUsernameEditText, mSignUpEmailEditText, mSignUpPasswordEditText, mSignUpPasswordRepeatEditText;
    private EditText mLoginEmailEditText, mLoginPasswordEditText;
    private LinearLayout mLoginContainer, mSignupContainer;
    private Button mLoginButton, mSignUpButton;
    private TextView mMessagesTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();

        mAuth = FirebaseAuth.getInstance();

        //Add password retyping validation
        mSignUpPasswordRepeatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!mSignUpPasswordEditText.getText().toString().equals(mSignUpPasswordRepeatEditText.getText().toString())){
                    mSignUpPasswordEditText.setBackground(getResources().getDrawable(R.drawable.et_backgrund_error));
                    mSignUpPasswordRepeatEditText.setBackground(getResources().getDrawable(R.drawable.et_backgrund_error));
                }else {
                    mSignUpPasswordEditText.setBackground(getResources().getDrawable(R.drawable.et_background_success));
                    mSignUpPasswordRepeatEditText.setBackground(getResources().getDrawable(R.drawable.et_background_success));
                }
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLoginInputs()){
                    String emailString = mLoginEmailEditText.getText().toString();
                    String passwordString = mLoginPasswordEditText.getText().toString();
                    loginUser(emailString, passwordString);
                }else {
                    mMessagesTextView.setText("Fields cannot be empty.");
                }
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateSignUpInputs()){

                    String signUpUserEmailStr = mSignUpEmailEditText.getText().toString();
                    String signUpUsernameStr = mSignupUsernameEditText.getText().toString();
                    String signUpUserPasswrodStr = mSignUpPasswordEditText.getText().toString();

                    signUpUser(signUpUsernameStr, signUpUserEmailStr, signUpUserPasswrodStr);
                }else {
                    mMessagesTextView.setText("Fields cannot be empty.");
                }

            }
        });

    }

    private boolean validateLoginInputs(){
        return !(mLoginPasswordEditText.getText().toString().equals("") || mLoginEmailEditText.getText().toString().equals(""));
    }

    private boolean validateSignUpInputs(){
        return !(mSignUpEmailEditText.getText().toString().equals("")||mSignUpPasswordEditText.getText().toString().equals(""));
    }

    private void loginUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        mMessagesTextView.setText("Login Failed, Try again");
                    }

                }
            });
    }

    private void signUpUser(String username, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "User created with email and password");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        mMessagesTextView.setText("Signup Failed, Try again");
                    }

                }
            });
    }

    private void setUpViews(){
        mMessagesTextView = findViewById(R.id.tv_messages);
        mSignupUsernameEditText = findViewById(R.id.et_sign_up_username);
        mSignUpEmailEditText = findViewById(R.id.et_sign_up_email);
        mSignUpPasswordEditText = findViewById(R.id.et_sign_up_password);
        mSignUpPasswordRepeatEditText = findViewById(R.id.et_sign_up_password_repeat);
        mLoginEmailEditText = findViewById(R.id.et_login_email);
        mLoginPasswordEditText = findViewById(R.id.et_login_password);
        mLoginContainer = findViewById(R.id.login_container);
        mSignupContainer = findViewById(R.id.sign_up_container);
        mLoginButton = findViewById(R.id.button_login);
        mSignUpButton = findViewById(R.id.button_signup);
    }

    public void switchToLogin(View v){
        mSignupContainer.setVisibility(View.GONE);
        mLoginContainer.setVisibility(View.VISIBLE);
    }

    public void switchToSignUp(View v){
        mSignupContainer.setVisibility(View.VISIBLE);
        mLoginContainer.setVisibility(View.GONE);
    }

}
