package apps.issy.com.jono;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by issy on 01/07/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityEspressoTest {

    private String user_email = "issyzac.iz@gmail.com";
    private String user_password_correct = "2010issy";
    private String user_password_Wrong = "2018issy";

    @Rule
    public ActivityTestRule<LoginSignUpActivity> mActivityRule =
            new ActivityTestRule<>(LoginSignUpActivity.class);


    @Test
    public void testLoginPage() {
        onView(withId(R.id.login_app_name))
                .check(matches(withText("Jono")));

        onView(withId(R.id.button_login))
                .check(matches(withText("Login")));

        onView(withId(R.id.switch_to_sign_up_text))
                .check(matches(withText("Sign Up")));
    }


    @Test
    public void testSuccessLogin() {
        // Type text and then press the button.
        onView(withId(R.id.et_login_email))
                .perform(typeText(user_email), closeSoftKeyboard());

        onView(withId(R.id.et_login_password))
                .perform(typeText(user_password_correct), closeSoftKeyboard());

        onView(withId(R.id.button_login))
                .perform(click());

    }

    @Test
    public void testFailedLogin() {
        // Type text and then press the button.
        onView(withId(R.id.et_login_email))
                .perform(typeText(user_email), closeSoftKeyboard());

        onView(withId(R.id.et_login_password))
                .perform(typeText(user_password_Wrong), closeSoftKeyboard());

        onView(withId(R.id.button_login))
                .perform(click());

    }


}
