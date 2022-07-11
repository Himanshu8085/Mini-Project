package com.example.bt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> devices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String s;
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0062CC")));
        status = findViewById(R.id.textView5);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devices = bluetoothAdapter.getBondedDevices();
        ArrayList<String> arr = new ArrayList<>();
        try {
            for (BluetoothDevice ble : devices){
                arr.add(ble.getName()+"\n"+ble.getAddress());
            }
            ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arr);
            ListView listView = findViewById(R.id.list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    s = (String) parent.getItemAtPosition(position);
                    String [] parts = s.split("\n");
                    String address = parts[1];
                    Toast.makeText(MainActivity.this,"Connecting to "+parts[0], Toast.LENGTH_SHORT).show();
                    Connectto(address);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Bluetooth Disconnected", Toast.LENGTH_SHORT).show();
            status.setText("Disconnected");
            status.setTextColor(Color.parseColor("#FF0000"));
        }
    }
    private void Connectto(String add){
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(add);
        BluetoothSocket bsoc = null;
        try {
            bsoc = device.createInsecureRfcommSocketToServiceRecord(myUUID);
            bsoc.connect();
            Toast.makeText(MainActivity.this, "Connected Successfully", Toast.LENGTH_SHORT).show();
            status.setText("Connected");
            status.setTextColor(Color.parseColor("#00FF00"));
        } catch (IOException e) {
            e.printStackTrace();
            status.setText("Disconnected");
            status.setTextColor(Color.parseColor("#FF0000"));
            Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
        }


        ToggleButton t1 = findViewById(R.id.led1);
        ToggleButton t2 = findViewById(R.id.led2);
        ToggleButton t3 = findViewById(R.id.led3);
        ToggleButton t4 = findViewById(R.id.led4);
        BluetoothSocket finalBsoc = bsoc;
        // LED 1
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.isChecked()){
                    try {
                        finalBsoc.getOutputStream().write("1".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        finalBsoc.getOutputStream().write("5".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // LED 2
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t2.isChecked()){
                    try {
                        finalBsoc.getOutputStream().write("2".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        finalBsoc.getOutputStream().write("6".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // LED 3
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t3.isChecked()){
                    try {
                        finalBsoc.getOutputStream().write("3".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        finalBsoc.getOutputStream().write("7".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // LED 4
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t4.isChecked()){
                    try {
                        finalBsoc.getOutputStream().write("4".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        finalBsoc.getOutputStream().write("8".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}