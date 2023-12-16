package com.example.amal_regaieg_lsi3_mesure_glycemie.model;

public class Patient {
    private int age;
    private double ValMes;
    private boolean jeune;
    private String result;


    // update controller => model
    public Patient (int age, double ValMes, boolean jeune) {
        this.age = age;
        this.ValMes = ValMes;
        this.jeune = jeune;
        calculer();
    }
    public int getAge() {
        return age;
    }
    public double getValMes() {
        return ValMes;
    }
    public boolean isJeune() {
        return jeune;
    }

    public void calculer() {
        if(jeune) {    // il est a jeun
            if(age >= 13) {
                if (ValMes < 5.0)
                    result = "Le niveau de glycèmie est bas !";
                else if (ValMes >= 5.0 && ValMes <= 7.2)
                    result = "Le niveau de glycèmie est normal !";
                else
                    result = "Le niveau de glycèmie est élevé !";
            }
            else
            if(age >= 6 && age <= 12) {
                if (ValMes < 5.0)
                    result = "Le niveau de glycèmie est bas !";
                else
                if (ValMes >= 5.0 && ValMes <= 10.0)
                    result = "Le niveau de glycèmie est normal !";
                else
                    result = "Le niveau de glycèmie est élevé !";
            }
            else
            if(ValMes < 5.50)
                result = "Le niveau de glycèmie est bas !";
            else
            if(ValMes >= 5.5 && ValMes <= 10.0)
                result = "Le niveau de glycèmie est normal !";
            else
                result = "Le niveau de glycèmie est élevé !";
        }
        else
        if(ValMes <= 10.5)
            result = "Le niveau de glycèmie est normal !";
        else
            result = "Le niveau de glycèmie est élevé !";
    }


    // update Patient => Controller
    public String getResult() {
        return result;
    }
}

