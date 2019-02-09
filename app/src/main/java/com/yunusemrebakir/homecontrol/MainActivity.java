package com.yunusemrebakir.homecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ListView listView;
    public static List<SwitchItem> switchItemList;
    public static List<SeekBarItem> seekBarItemList;
    public static MergeAdapter mergeAdapter;
    public static SeekBarAdapter seekBarAdapter;
    public static SwitchAdapter switchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.main_listview);

        //Load data from shared prefs.
        switchItemList = (List<SwitchItem>) Utilities.deserialize("switches", getApplicationContext(), new TypeToken<List<SwitchItem>>() {}.getType());
        seekBarItemList = (List<SeekBarItem>) Utilities.deserialize("seekBars", getApplicationContext(), new TypeToken<List<SeekBarItem>>() {}.getType());

        mergeAdapter = new MergeAdapter();
        seekBarAdapter = new SeekBarAdapter(getApplicationContext(), seekBarItemList);
        switchAdapter = new SwitchAdapter(getApplicationContext(), switchItemList);
        mergeAdapter.addAdapter(switchAdapter);
        mergeAdapter.addAdapter(seekBarAdapter);
        listView.setAdapter(mergeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_addSwitchControl) {
            Intent intent = new Intent(getApplicationContext(), AddSwitchActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_addSeekBarControl) {
            Intent intent = new Intent(getApplicationContext(), AddSeekBarActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}


