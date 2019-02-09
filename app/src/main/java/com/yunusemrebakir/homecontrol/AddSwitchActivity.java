package com.yunusemrebakir.homecontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.suke.widget.SwitchButton;

import java.util.List;

import static com.yunusemrebakir.homecontrol.MainActivity.switchAdapter;
import static com.yunusemrebakir.homecontrol.MainActivity.switchItemList;

public class AddSwitchActivity extends AppCompatActivity {

    private Button btn_addSwitch_addSwitch;
    private Button btn_addSwitch_cancel;
    private TextView txt_addWidget_initialSwitchState;
    private EditText et_addSwitch_publishValueOn;
    private EditText et_addSwitch_switchName;
    private EditText et_addSwitch_publishValueOff;
    private EditText et_addSwitch_publishTopic;
    private SwitchButton switch_addSwitch_switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_switch);
        setTitle("Switch Properties");

        btn_addSwitch_addSwitch = (Button) findViewById(R.id.btn_addSwitch_addSwitch);
        btn_addSwitch_cancel = (Button) findViewById(R.id.btn_addSwitch_cancel);
        txt_addWidget_initialSwitchState = (TextView) findViewById(R.id.txt_addWidget_initialSwitchState);
        et_addSwitch_publishValueOn = (EditText) findViewById(R.id.et_addSwitch_publishValueOn);
        et_addSwitch_publishTopic = (EditText) findViewById(R.id.et_addSwitch_publishTopic);
        et_addSwitch_publishValueOff = (EditText) findViewById(R.id.et_addSwitch_publishValueOff);
        switch_addSwitch_switchState = (SwitchButton) findViewById(R.id.switch_addSwitch_switchState);
        et_addSwitch_switchName = (EditText) findViewById(R.id.et_addSwitch_switchName);

        switch_addSwitch_switchState.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    txt_addWidget_initialSwitchState.setText("ON");
                } else {
                    txt_addWidget_initialSwitchState.setText("OFF");
                }
            }
        });

        btn_addSwitch_addSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                switchAdapter.updateList(switchItemList);
                finish();
            }
        });

        btn_addSwitch_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void saveData() {

        switchItemList = (List<SwitchItem>) Utilities.deserialize("switches", getApplicationContext(),new TypeToken<List<SwitchItem>>() {}.getType());

        //Add switch item parameters to the list
        SwitchItem switchItem = new SwitchItem(et_addSwitch_switchName.getText().toString(), switch_addSwitch_switchState.isChecked(),
                et_addSwitch_publishTopic.getText().toString(), et_addSwitch_publishValueOn.getText().toString(), et_addSwitch_publishValueOff.getText().toString());

        switchItemList.add(switchItem);

        //Serialize the list and save as shared prefs.
        Utilities.serializeAndSave(switchItemList, "switches", getApplicationContext());
    }

    //TODO switch üzerine long-press olunca edit açılması feature'ı implement edildiğinde bu metodu düzeltip kullan
    public void loadDataForEditing() {
    }
}
