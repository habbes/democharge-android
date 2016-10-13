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
import xyz.habbes.democharge.activities.helpers.ToastMessage;
import xyz.habbes.democharge.core.models.User;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {



    private EditText nameView;
    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private View progressView;
    private View registerFormView;
    private View loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameView = (EditText) findViewById(R.id.registerName);
        emailView = (AutoCompleteTextView) findViewById(R.id.registerEmail);

        passwordView = (EditText) findViewById(R.id.registerPassword);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });

        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        loginLink = findViewById(R.id.loginLinkLabel);
        loginLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        registerFormView = findViewById(R.id.registerForm);
        progressView = findViewById(R.id.registerProgress);
    }


    /**
     * registers user based on user input in the forms
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    private void registerUser(){
        String name = nameView.getText().toString();
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        User user = new User(name, email, password);
        showProgress(true);
        User.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                showProgress(false);
                User registeredUser = response.body();
                if(registeredUser == null){
                    ToastMessage.showError(getApplicationContext(),
                            getResources().getString(R.string.error_registration));
                }
                else {
                    ToastMessage.show(getApplicationContext(),
                            getResources().getString(R.string.registration_successful));

                    // go to log in
                    openLoginActivity();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showProgress(false);
                ToastMessage.showError(getApplicationContext(), t.getMessage());
            }
        });
    }

    /**
     * starts the login activity
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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

            registerFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            registerFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    registerFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            registerFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}

