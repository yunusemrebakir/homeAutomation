package com.yunusemrebakir.homecontrol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText etServerAddress;
    private EditText etServerPort;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        etServerAddress = (EditText) findViewById(R.id.etServerAddress);
        etServerPort = (EditText) findViewById(R.id.etServerPort);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        /* Load settings */
        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void saveData() {
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("etServerAddress", etServerAddress.getText().toString());
        editor.putString("etServerPort", etServerPort.getText().toString());
        editor.commit();
    }


    public void loadData() {
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        etServerAddress.setText(settings.getString("etServerAddress", ""));
        etServerPort.setText(settings.getString("etServerPort", ""));
    }


}
