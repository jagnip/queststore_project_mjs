package com.codecool_mjs.controller;

import com.codecool_mjs.view.LoginView;
import com.codecool_mjs.dataaccess.dao.LoginDao;
import com.codecool_mjs.model.Login;

import java.sql.SQLException;

public class LoginController implements Loginable{

    LoginDao loginDao;

    public LoginController() {

        this.loginDao = new LoginDao();
    }

    public void startController() {

        Login user;
        Boolean isRunning = true;

        LoginView.print("Welcome to CoinMasters 2.0");

        while (isRunning) {
            user = getLoginData();
            login(user);
        }
    }

    @Override
    public void login(Login user) {

        String profession;
        Integer id;

        id = user.getId();
        profession = user.getProfession();

        if (profession.equals("admin")) {
            AdminMenuController adminMenuController = new AdminMenuController();
            adminMenuController.startController();

        } else if (profession.equals("mentor")) {
            System.out.println("Mentor Controller");
            MentorMenuController mentorMenuController = new MentorMenuController();
            mentorMenuController.startController();
        } else if (profession.equals("codecooler")) {
            System.out.println("Codecooler Controller");
//            CodecoolerMenuController codecoolerMenuController = new CodecoolerMenuController(id);
//            codecoolerMenuController.startController();
        } else {
            LoginView.print("Wrong email or password!");
        }
    }

    private Login getLoginData() {

        String email;
        String password;
        Login user;

        email = LoginView.getEmail();
        password = LoginView.getPassword();

        user = this.loginDao.getLoginData(email, password);

        return user;
    }
}
