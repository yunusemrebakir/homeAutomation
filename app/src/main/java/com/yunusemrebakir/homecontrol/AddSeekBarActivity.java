package com.yunusemrebakir.homecontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.yunusemrebakir.homecontrol.MainActivity.seekBarAdapter;
import static com.yunusemrebakir.homecontrol.MainActivity.seekBarItemList;

public class AddSeekBarActivity extends AppCompatActivity {

    private Button btn_addSeekBar_addSeekBar;
    private Button btn_addSeekBar_cancel;
    private SeekBar sb_addSeekBar_seekBar;
    private EditText et_addSeekBar_publishAddress;
    private EditText et_addSeekBar_seekBarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seekbar);
        setTitle("Seekbar Properties");

        btn_addSeekBar_addSeekBar = (Button) findViewById(R.id.btn_addSeekBar_addSeekBar);
        btn_addSeekBar_cancel = (Button) findViewById(R.id.btn_addSeekBar_cancel);
        sb_addSeekBar_seekBar = (SeekBar) findViewById(R.id.sb_addSeekBar_seekBar);
        et_addSeekBar_publishAddress = (EditText) findViewById(R.id.et_addSeekBar_publishAddress);
        et_addSeekBar_seekBarName = (EditText) findViewById(R.id.et_addSeekBar_seekBarName);


        btn_addSeekBar_addSeekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();

                seekBarAdapter.updateList(seekBarItemList);

                finish();
            }
        });

        btn_addSeekBar_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void saveData() {

        seekBarItemList = (List<SeekBarItem>) Utilities.deserialize("seekBars", getApplicationContext(),new TypeToken<List<SeekBarItem>>() {}.getType());

        //Add seekBar item parameters to the list
        SeekBarItem seekBarItem = new SeekBarItem(et_addSeekBar_seekBarName.getText().toString(), sb_addSeekBar_seekBar.getProgress(), et_addSeekBar_publishAddress.getText().toString());
        seekBarItemList.add(seekBarItem);

        //Serialize the list and save as shared prefs.
        Utilities.serializeAndSave(seekBarItemList, "seekBars", getApplicationContext());

        //TODO switch üzerine long-press olunca edit açılması feature'ı implement edildiğinde bu metodu düzeltip kullan

    }

}
