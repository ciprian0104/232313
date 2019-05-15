package com.example.ekgapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class Server {

    protected String authenticate(String user, String password){
        return "{Nume=Papa; Prenume=Gal; Varsta=33}";   //aici vom avea mai tarziu http request

    }

    protected JSONObject getProfile(int ID){
        JSONObject obj = new JSONObject();   //aici se va implementa http request cand voi avea datele
        try {
        obj.put("name", "foo");    //name,num, balance sunt parametrii fake si cand am reali ii voi pune pe aceia
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));

            obj.put("is_vip", new Boolean(true));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

        return obj;
    }

    protected boolean updateUserProfile(){
        boolean result = false;
        JSONObject obj = new JSONObject();   //aici se va implementa http request cand voi avea datele
        try {
            obj.put("name", "foo");    //name,num, balance sunt parametrii fake si cand am reali ii voi pune pe aceia
            obj.put("num", new Integer(100));
            obj.put("balance", new Double(1000.21));

            obj.put("is_vip", new Boolean(true));
            //new http request cu datele de mai sus
            result=true; //daca am ajuns aici, totul a fost ok

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

        return result;
    }

    protected String readUserRecomandations(){
    return "exemplu de recomandare";
    }

    protected boolean uploadUserHealthData(){
        boolean result=false;
       /* try{
            //new http request
            result=true;
        }catch()*///http request ne spune ce va fi aici in try catch
    return result;
    }

    //returns puls, ecg, temperatura, umiditate, etc
    protected HealthData receiveUserHealthData(){

        List pulsuri = Collections.singletonList(new int[]{1, 2, 3, 4});
        List temperatura = Collections.singletonList(new int[]{37, 38, 39, 40});
        List umiditate = Collections.singletonList(new int[]{30, 50, 70, 45});
        //List frecvente = Collections.singletonList(new ECGdata(15.0, 17.0) );

        return new HealthData();

    }
}
