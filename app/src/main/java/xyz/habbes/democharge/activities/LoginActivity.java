package xyz.habbes.democharge.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.habbes.democharge.R;
import xyz.habbes.democharge.activities.helpers.LoginService;
import xyz.habbes.democharge.activities.helpers.ToastMessage;
import xyz.habbes.democharge.core.models.AccessToken;
import xyz.habbes.democharge.core.models.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "Democharge/Login";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        registerLink = findViewById(R.id.registerLinkLabel);
        registerLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        // go to main activity is user logged in
        redirectIfLoggedIn();
    }

    /**
     * attempts to login with email and password
     * from the form
     * @author habbes
     * @added 13.10.2016
     * @version 1
     */
    private void attemptLogin(){
        showProgress(true);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        User.login(email, password).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                showProgress(false);
                AccessToken token = response.body();
                if(token == null){
                    if(response.code() == 401){
                        ToastMessage.showError(getApplicationContext(),
                                getResources().getString(R.string.error_incorrect_email_password));
                    }
                    else {
                        ToastMessage.showError(getApplicationContext(),
                                getResources().getString(R.string.error_login_failed));
                    }
                }
                else {
                    // persist token
                    LoginService.saveLogin(getApplicationContext(), token);

                    ToastMessage.show(LoginActivity.this,
                            String.format(getResources().getString(R.string.message_welcome),
                                    token.user.name));
                    // show main activity
                    openMainActivity();
                }

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                showProgress(false);
                ToastMessage.show(getApplicationContext(), t.getMessage());
            }
        });
    }

    /**
     * starts the registration page activity
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    private void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * starts the main activity
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    private void openMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * redirect to main activity if there is a user already logged in
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    private void redirectIfLoggedIn(){
        if(LoginService.getCurrentToken(this) != null){
            openMainActivity();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

