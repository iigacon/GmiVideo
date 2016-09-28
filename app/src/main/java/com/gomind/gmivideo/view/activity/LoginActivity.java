package com.gomind.gmivideo.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.Movie;
import com.gomind.data.entities.Session;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerLoginComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.GetSessionModule;
import com.gomind.gmivideo.Injector.Module.LoginModule;
import com.gomind.gmivideo.Injector.Module.RequestTokenModule;
import com.gomind.gmivideo.Injector.Module.WatchListMovieModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.ulti.WatchlistMovie;
import com.gomind.gmivideo.vmp.presenter.LoginPresenter;
import com.gomind.gmivideo.vmp.presenter.WatchListMoviePresenter;
import com.gomind.gmivideo.vmp.ulti.AccountStatus;
import com.gomind.gmivideo.vmp.view.LoginView;
import com.gomind.gmivideo.vmp.view.MovieBaseView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginView, MovieBaseView{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.input_email)
    EditText emailText;
    @BindView(R.id.input_password)
    EditText passwordText;
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.link_signup)
    TextView signupLink;
    ProgressDialog progressDialog;
    @Inject
    WatchListMoviePresenter watchListMoviePresenter;

    SharedPreferences sharedpreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerLoginComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .loginModule(new LoginModule())
                .requestTokenModule(new RequestTokenModule())
                .getSessionModule(new GetSessionModule())
                .watchListMovieModule(new WatchListMovieModule())
                .build().inject(this);
        loginButton.setOnClickListener(v -> actionlogin());

        signupLink.setOnClickListener(v -> {
            // Start the Signup activity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
        });
        sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("login", true)){
            login(sharedpreferences.getString("username", null), sharedpreferences.getString("password", null));
        }
    }
    public void actionlogin(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        login(email, password);
    }
    public void login(String username, String password) {
        Log.d(TAG, "Login");
        validate();

//        loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();



        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("login", false);
        editor.apply();
        loginPresenter.setUsername(username);
        loginPresenter.setPassword(password);

        loginPresenter.attachView(this);
        loginPresenter.onCreate();
        // TODO: Implement your own authentication logic here.

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
//        moveTaskToBack(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || email.length()<4) {
            emailText.setError("enter a valid id");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            passwordText.setError("between 4 and 20 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }


    @Override
    public void bindSession(Session session) {
        if(session.isSuccess()){
            AccountStatus.session_id=session.getSession_id();

        }
    }

    @Override
    public void bindError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void bindErrorUser(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindAccount(Account account) {
        AccountStatus.user_id=account.getId();
        watchListMoviePresenter.setId(account.getId());
        watchListMoviePresenter.setSession_id(AccountStatus.session_id);
        watchListMoviePresenter.attachView(this);
        watchListMoviePresenter.onCreate();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("login", true);
        editor.apply();
    }


    @Override
    public void bindMovieBase(List<Movie> movies) {
        if(movies.size()!=0) {
            WatchlistMovie.store(movies);
        }
        if(movies.size()!=20){
            MainActivity.start(this, AccountStatus.session_id);
            progressDialog.dismiss();
            finish();
        }else{
            watchListMoviePresenter.loadMore();
        }

    }

    @Override
    public void bindLoadMore(int count) {
        if(count==20){
            watchListMoviePresenter.loadMore();
        }else{
            MainActivity.start(this, AccountStatus.session_id);
            progressDialog.dismiss();
            finish();
        }
    }

    @Override
    public void showDetailMovie(String id) {

    }
}
