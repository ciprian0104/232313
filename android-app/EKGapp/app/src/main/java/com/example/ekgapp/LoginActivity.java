package com.example.ekgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText CNP;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter =5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CNP =(EditText)findViewById(R.id.etID);
        Password =(EditText)findViewById(R.id.etPassword);
        Info =(TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.butonLogin);
        Info.setText("Numar de incercari ramase: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(CNP.getText().toString(),Password.getText().toString());
            }
        });
    }



    private void validate(String userID, String userPassword){
        if((userID.equals("person@email.com")) && (userPassword.equals("123456789"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            counter --;
            Info.setText("Numar de incercari ramase: " + String.valueOf(counter));
            Toast.makeText(getApplicationContext(), "person@email.com, 123456789",
                    Toast.LENGTH_LONG).show();
            if(counter==0){
                Login.setEnabled(false);
            }
        }
    }
}
