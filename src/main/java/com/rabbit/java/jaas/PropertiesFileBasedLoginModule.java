package com.rabbit.java.jaas;


import com.sun.security.auth.UserPrincipal;
import org.springframework.util.StringUtils;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.CredentialException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ethan Zhang on 15/6/3.
 */
public class PropertiesFileBasedLoginModule implements LoginModule {

    private CallbackHandler callbackHandler;

    private Subject subject;

    private Properties properties = new Properties();

    private boolean authSuceed = false;

    private String username;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
        String propsFilePath = (String) options.get("properties.file.path");
        File propsFile = new File(propsFilePath);
        try {
            properties.load(new FileInputStream(propsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login() throws LoginException {
        TextInputCallback usernameCallback = new TextInputCallback("用户名：");
        TextInputCallback passwordCallback = new TextInputCallback("密码：");
        try {
            callbackHandler.handle(new Callback[] {usernameCallback, passwordCallback});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String username = usernameCallback.getText();

        if (StringUtils.isEmpty(username)) {
            throw new LoginException("用户名为空！");
        }

        if (!properties.contains(username)) {
            throw new AccountException("该用户不存在");
        }

        String password = passwordCallback.getText();

        if (StringUtils.isEmpty(password)) {
            throw new CredentialException("密码为空！");
        }

        if (!password.equals(properties.get(username))) {
            throw new FailedLoginException("密码不对！");
        }

        authSuceed = true;
        this.username = username;
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        if (authSuceed) {
            this.subject.getPrincipals().add(new UserPrincipal(username));
            username = null;
            authSuceed = false;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        username = null;
        if (authSuceed) {
            authSuceed = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        Set<Principal> principals = subject.getPrincipals();
        Set<UserPrincipal> userPrincipals = subject.getPrincipals(UserPrincipal.class);

        for (UserPrincipal userPrincipal : userPrincipals) {
            if (userPrincipal.getName().equals(username)) {
                principals.remove(userPrincipal);
                break;
            }
        }
        return true;
    }
}
