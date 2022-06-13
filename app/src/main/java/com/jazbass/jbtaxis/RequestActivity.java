package com.jazbass.jbtaxis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class RequestActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView txtName, txtSurname, txtCarRegistration, txtMinutes;
    Button btnConfirm;
    String name, surname, carRegistration;
    int minutes;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString("name","");
        surname = preferences.getString("surname","");
        carRegistration = this.getIntent().getExtras().getString("carRegistration");
        minutes = 9;
        txtName = findViewById(R.id.txtName);
        txtName.setText(name);
        txtSurname = findViewById(R.id.txtSurname);
        txtSurname.setText(surname);
        txtCarRegistration = findViewById(R.id.txtCarResgistration);
        txtCarRegistration.setText(carRegistration);
        txtMinutes = findViewById(R.id.txtMinutes);
        txtMinutes.setText(minutes+"");
        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrincipalActivity.assistantSQL.newRegist(carRegistration, name, surname);
                setContentView(R.layout.activity_inprogress);
                Intent i = new Intent(RequestActivity.this, TimerService.class);
                i.putExtra("waitTime",minutes);
                startService(i);
            }
        });
    }
}
