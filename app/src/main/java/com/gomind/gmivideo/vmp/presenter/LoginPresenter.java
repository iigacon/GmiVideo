package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.Login;
import com.gomind.data.entities.RequestToken;
import com.gomind.data.entities.Session;
import com.gomind.domain.GetAccount;
import com.gomind.domain.GetLogin;
import com.gomind.domain.GetRequestToken;
import com.gomind.domain.GetSession;
import com.gomind.gmivideo.vmp.view.LoginView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/16/16.
 */
public class LoginPresenter implements Presenter {
    private GetRequestToken requestToken;
    private GetLogin login;
    private GetSession getSession;
    private GetAccount getAccount;
    private LoginView loginView;
    private Subscription mRequestToken;
    private Subscription mLogin;
    private String username;
    private String password;
    @Inject
    public LoginPresenter(GetRequestToken requestToken, GetLogin login, GetSession getSession, GetAccount getAccount) {
        this.requestToken = requestToken;
        this.login = login;
        this.getSession=getSession;
        this.getAccount = getAccount;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        loginView= (LoginView) v;
    }

    @Override
    public void onCreate() {
        mRequestToken=requestToken.execute().subscribe(this::OnReceivedRequestToken,this::OnError);
    }

    @Override
    public void loadMore() {

    }
    public void OnReceivedRequestToken(RequestToken requestToken){
        if(requestToken.isSuccess()){
            actionLogin(requestToken.getRequest_token(),username, password);
        }else{
            loginView.bindErrorUser("Loi ket noi voi server");
        }
    }
    public void OnError(Throwable throwable){
        loginView.bindError(throwable);
    }
    public void actionLogin(String request_token,String username, String password){
        login.setUsername(username);
        login.setPassword(password);
        login.setRequest_token(request_token);
        mLogin=login.execute().subscribe(this::OnReceivedLogin, this::OnError);
    }
    public void OnReceivedLogin(Login login){
        if(login.isSuccess()){
            actionGetSession(login.getRequest_token());
        }else loginView.bindErrorUser("Loi username hoac password.\nVui long nhap lai!");
    }
    public void actionGetSession(String request_token){
        getSession.setRequest_token(request_token);
        getSession.execute().subscribe(this::OnReceivedSession, this::OnError);
    }
    public void OnReceivedSession(Session session){
        if(session.isSuccess()){
            loginView.bindSession(session);
            actionAccount(session.getSession_id());
        }
    }
    public void actionAccount(String session_id){
        getAccount.setSession_id(session_id);
        getAccount.execute().subscribe(this::OnReceivedAccount, this::OnError);
    }
    public void OnReceivedAccount(Account account){
        loginView.bindAccount(account);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
