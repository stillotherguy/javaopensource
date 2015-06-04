package com.rabbit.java.jaas;

import com.sun.security.auth.callback.TextCallbackHandler;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by Ethan Zhang on 15/6/3.
 */
public class MyApp {

    //启动参数java.security.auth.login.config指定配置文件位置
    private LoginContext loginContext;

    public MyApp() throws LoginException {
        TextCallbackHandler callbackHandler = new TextCallbackHandler();
        loginContext = new LoginContext("MyApp", callbackHandler);
    }

    public Subject login() throws LoginException {
        loginContext.login();
        return loginContext.getSubject();
    }

    public void logout() throws LoginException {
        loginContext.logout();
    }
}
