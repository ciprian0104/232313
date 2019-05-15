package com.example.ekgapp;

import java.util.List;

public class HealthData {


    List pulsuri;
    List temperatura;
    List umiditate;
    List frecvente;

    public void healthData(){

    }

    public List getPulsuri() {
        return pulsuri;
    }

    public void setPulsuri(List pulsuri) {
        this.pulsuri = pulsuri;
    }

    public List getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(List temperatura) {
        this.temperatura = temperatura;
    }

    public List getUmiditate() {
        return umiditate;
    }

    public void setUmiditate(List umiditate) {
        this.umiditate = umiditate;
    }

    public List getFrecvente() {
        return frecvente;
    }

    public void setFrecvente(List frecvente) {
        this.frecvente = frecvente;
    }


}
