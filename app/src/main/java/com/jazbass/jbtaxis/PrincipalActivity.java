package com.jazbass.jbtaxis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    static ArrayList<Taxi> availablesTaxis;
    int PERMISSIONS_CODE = 1;
    String[] Permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (!hasPermissions(Permissions)){
            ActivityCompat.requestPermissions(PrincipalActivity.this, Permissions, PERMISSIONS_CODE);
        }else {
            try {
                ServerTaskXML serverTaskXML = new
                        ServerTaskXML("http://desarrollo.cepibase.es/taxis/disponibles.php");
                        serverTaskXML.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean hasPermissions(String [] permissions){
        if (permissions != null){
            for (String permission:permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission)!=
                        PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            try {
                ServerTaskXML serverTaskXML = new
                        ServerTaskXML("http://desarrollo.cepibase.es/taxis/disponibles.php");
                serverTaskXML.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            ActivityCompat.requestPermissions(PrincipalActivity.this, Permissions, PERMISSIONS_CODE);
        }
    }
}
