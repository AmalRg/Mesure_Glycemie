package com.example.amal_regaieg_lsi3_mesure_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amal_regaieg_lsi3_mesure_glycemie.R;

public class ConsultActivity extends AppCompatActivity {

    private TextView TvRep;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        init();
        // resultat dans ConsultActivity
        Intent intent = getIntent();
        String reponse = intent.getStringExtra("reponse");
        TvRep.setText(reponse);

        // bouton retour au mainActivity
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (reponse != null) {
                    setResult(RESULT_OK, intent); // = -1 // Définit le résultat comme RESULT_OK
                }
                else {
                    setResult(RESULT_CANCELED, intent); // = 0 // Définit le résultat comme RESULT_CANCELED
                }
                finish(); // Termine l'activité actuelle
            }
        });
    }



    private void init() {
        TvRep = (TextView) findViewById(R.id.tvResult);
        btnReturn = (Button) findViewById(R.id.btnReturn);
    }


}