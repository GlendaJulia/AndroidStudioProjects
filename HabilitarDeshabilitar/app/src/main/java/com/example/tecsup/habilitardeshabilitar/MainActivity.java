package com.example.tecsup.habilitardeshabilitar;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch wifiSwitch;
    private WifiManager wifiManager;
    private BluetoothAdapter bluetoothAdapter;
    private Switch blueSwitch;
    private Switch datosSwitch;
    private TelephonyManager telephonyService;
    TextView textview;
    Button button;
    IntentFilter intentfilter;
    int deviceStatus;
    String currentBatteryStatus="Informacion";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiSwitch = (Switch) findViewById(R.id.wifi_switch);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        blueSwitch = (Switch) findViewById(R.id.bluetooth_switch);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        datosSwitch = (Switch) findViewById(R.id.datos_switch);
        telephonyService = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        button = (Button)findViewById(R.id.buttonBatteryStatus);
        textview = (TextView)findViewById(R.id.textViewBatteryStatus);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.registerReceiver(broadcastreceiver,intentfilter);

            }
        });

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("WiFi Encendido");
                } else {
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("WiFi Apagado");

                }
            }
        });

        blueSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    blueSwitch.setText("Bluetooth Prendido");
                    bluetoothAdapter.enable();

                } else {
                    blueSwitch.setText("Bluetooth Apagado");
                    bluetoothAdapter.disable();
                }
            }
        });

        datosSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    telephonyService.setDataEnabled(true);
                    datosSwitch.setText("Datos Encendido");
                } else {
                    telephonyService.setDataEnabled(false);
                    datosSwitch.setText("Datos Apagado");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);

        Intent turnOnIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
        startActivityForResult( turnOnIntent, 1 );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("WiFi Encendido");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("WiFi Apagado");
                    break;
            }
        }
    };

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                textview.setText(currentBatteryStatus+" = Cargado Al "+batteryLevel+" %");
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                textview.setText(currentBatteryStatus+" = Descargado Al "+batteryLevel+" %");
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                textview.setText(currentBatteryStatus+"= Bateria Llena Papu "+batteryLevel+" %");
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){
                textview.setText(currentBatteryStatus+" = Desconocido "+batteryLevel+" %");
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                textview.setText(currentBatteryStatus+" = No esta Cargando "+batteryLevel+" %");
            }
        }
    };
}
