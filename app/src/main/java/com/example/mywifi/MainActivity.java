package com.example.mywifi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.net.wifi.*;
import android.widget.TextView;
import java.lang.reflect.*;

public class MainActivity extends Activity {
    private WifiManager myManager;
    private WifiInfo infomation;
    private TextView ability;
    private TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        checkAbility();
        showDetails();

    }
    @TargetApi(21)
    public void checkAbility() {
        ability = findViewById(R.id.textView0);
        infomation = myManager.getConnectionInfo();
        boolean support5G = false;
        //support5G = myManager.is5GHzBandSupported();

        try {
        Class cls = Class.forName("android.net.wifi.WifiManager");
        Method method = cls.getMethod("isDualBandSupported");
        Object invoke = method.invoke(myManager);
        support5G = (boolean)invoke;}
        catch (Exception e){
            e.printStackTrace();

        }





        if (support5G == true) {
            ability.setText(" supports 5G");
        } else {
            ability.setText("5G is not supported");
        }
    }

    @TargetApi(21)
    public void showDetails() {
        details = findViewById(R.id.textView);
        float frequency = infomation.getFrequency() / 1000f;
        float speed = infomation.getLinkSpeed();
        //String frequence
        //details.setText( String.valueOf(frequency));
        details.setText(String.format("Connection:\nFrequence is : %.1f%s; Speed is : %.1f%s" ,
                frequency, "GHz", speed, WifiInfo.LINK_SPEED_UNITS));


    }





}
