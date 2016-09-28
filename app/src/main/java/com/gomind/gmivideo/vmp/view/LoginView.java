package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.Session;

/**
 * Created by Duc on 9/16/16.
 */
public interface LoginView extends View{
    void bindSession(Session session);
    void bindError(Throwable throwable);
    void bindErrorUser(String error);
    void bindAccount(Account account);
}
