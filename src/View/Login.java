package View;

import Repository.RepoFactory;
import Service.LoginService;

import javax.swing.*;

public class Login extends JFrame {

    private final LoginService loginService;

    public Login(RepoFactory repoFactory){
        loginService = new LoginService(repoFactory);
    }
}
