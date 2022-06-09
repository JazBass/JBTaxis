package com.jazbass.jbtaxis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class InitialActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    static SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        readPreferences();
        logoAnimation();
        panelAnimation();
    }
    //Animations
    void logoAnimation(){
        ImageView logo = findViewById(R.id.imageLogo);
        Animation show_logo = AnimationUtils.loadAnimation(this, R.anim.animation_logo);
        logo.startAnimation(show_logo);
    }

    void panelAnimation(){
        LinearLayout buttonPanel = findViewById(R.id.panelButton);
        Animation showPanel = AnimationUtils.loadAnimation(this, R.anim.animation_buttons);
        buttonPanel.startAnimation(showPanel);
    }

    //Preferences
    @SuppressLint("SetTextI18n")
    void readPreferences(){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.contains("nickname")){
            btnLogin.setVisibility(View.GONE);
        }else{
            btnRegister.setVisibility(View.GONE);
            btnLogin.setText(btnLogin.getText()+" "+preferences.getString("nickname",""));
        }
    }
    //Login and Register buttons functions
    public void loginOption(View v){
        Intent i = new Intent(InitialActivity.this, PrincipalActivity.class);
        startActivity(i);
    }
    public void registerOption(View v){
        Intent i = new Intent(InitialActivity.this, PreferencesActivity.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}


