package com.example.amal_regaieg_lsi3_mesure_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amal_regaieg_lsi3_mesure_glycemie.R;
import com.example.amal_regaieg_lsi3_mesure_glycemie.controller.LoginController;

public class HomeActivity extends AppCompatActivity {
    private Button btnConsultation;
    private EditText etUserName;
    private EditText etPassword;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        etUserName.setText(loginController.getUsername());
        etPassword.setText(loginController.getPassword());

        btnConsultation = (Button) findViewById(R.id.btnConsultation);

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent sans retour
                String userName, password;
                boolean verifUserName = false, verifPAssword = false;

                if (!etUserName.getText().toString().isEmpty())
                    verifUserName = true;
                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre nom d'utilisateur !", Toast.LENGTH_SHORT).show();

                if (!etPassword.getText().toString().isEmpty())
                    verifPAssword = true;
                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre mot de passe !", Toast.LENGTH_SHORT).show();

                if (verifPAssword && verifUserName) {
                    userName = etUserName.getText().toString();
                    password = etPassword.getText().toString();
                    loginController.createUser(userName, password, HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void init() {
        loginController = LoginController.getInstance((HomeActivity.this));
        btnConsultation = (Button) findViewById(R.id.btnConsultation);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);
    }
}