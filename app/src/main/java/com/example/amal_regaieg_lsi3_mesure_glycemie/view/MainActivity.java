package com.example.amal_regaieg_lsi3_mesure_glycemie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amal_regaieg_lsi3_mesure_glycemie.R;
import com.example.amal_regaieg_lsi3_mesure_glycemie.controller.Controller;


public class MainActivity extends AppCompatActivity
{
    private final int REQUEST_CODE = 1;
    private TextView tvage;
    private SeekBar sbAge;
    private RadioButton rbtOui;
    private RadioButton rbtNon;
    private EditText ValMes;
    private Button btnConsulter;
    private TextView TvRes;
    private Controller controller = Controller.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //listener SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                Log.i("Information", "onProgressChanged " + i);
                tvage.setText("Votre âge : "+ i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = sbAge.getProgress();
                float valMes;
                System.out.println("age =" + age);
                String valmesure = (ValMes.getText().toString());
                System.out.println("val =" + valmesure);
                if (age == 0 && valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre âge et une valeur meusurée !", Toast.LENGTH_SHORT).show();
                } else if (age == 0) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre âge !", Toast.LENGTH_SHORT).show();
                } else if (valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir une valeur mesurée valide !", Toast.LENGTH_SHORT).show();
                } else {
                    //float valMes = Float.parseFloat(valmesure);
                    valMes = Float.valueOf(ValMes.getText().toString());
                    boolean jeun = rbtOui.isChecked();

                    controller.createPatient(age, valMes, jeun);
                    // update controller to view

                    Intent intent = new Intent(MainActivity.this, ConsultActivity.class);
                    intent.putExtra("reponse", controller.getResultat());
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }
    private void init()
    {
        tvage = (TextView) findViewById(R.id.tvage);
        sbAge = (SeekBar) findViewById(R.id.sbAge);
        rbtOui = (RadioButton) findViewById(R.id.rbtOui);
        rbtNon = (RadioButton) findViewById(R.id.rbtNon);
        ValMes = (EditText) findViewById(R.id.ValMes);
        btnConsulter = (Button) findViewById(R.id.btnConsulter);
        TvRes = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "Une erreur s'est produite !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}