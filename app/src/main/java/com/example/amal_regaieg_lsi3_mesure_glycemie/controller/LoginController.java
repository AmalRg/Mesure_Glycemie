package com.example.amal_regaieg_lsi3_mesure_glycemie.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.amal_regaieg_lsi3_mesure_glycemie.model.User;

public class LoginController {
    private static final String SHARED_PREF = "sharedPefs";
    private static LoginController instance = null;

    private static User user;   // creation de l'instance LoginController apres la creation de user
    private LoginController() {
        super();
    }

    public static final LoginController getInstance(Context context) {
        if (LoginController.instance == null) {
            LoginController.instance = new LoginController();
        }
        // a l'appel du recapUser, on lui associe le context
        recapUser(context);

        return LoginController.instance;
    }


    private static void recapUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        // recuperation des deux attributs dans deux variables userName et password
        String userName = sharedPreferences.getString("userName", "");//s1 est la valeur par defaut
        String password = sharedPreferences.getString("password", "");
        // sauvegarde des attributs dans un nouveau user
        user = new User(userName, password);
    }

    public void createUser(String userName, String password, Context context) {
        user = new User(userName, password);
        // acces au sharedPrefs est privé
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Le fichier sharedPreference contient un couple (cle, valeur) de type string, ("userName" , Ahmed),
        // meme pour le password
        editor.putString("userName", userName);
        editor.putString("password", password);
        // commit avec apply
        editor.apply();
    }

    // appel de ces methodes getUsername et getpassword dans HomeActivity
    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }


}
