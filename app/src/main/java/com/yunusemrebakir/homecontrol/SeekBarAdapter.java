package com.yunusemrebakir.homecontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.yunusemrebakir.homecontrol.MainActivity.seekBarItemList;

public class SeekBarAdapter extends BaseAdapter {

    private Context mContext;
    private List<SeekBarItem> mSeekBarItemList;

    public SeekBarAdapter(Context mContext, List<SeekBarItem> mSeekBarItemList) {
        this.mContext = mContext;
        this.mSeekBarItemList = mSeekBarItemList;

    }

    @Override
    public int getCount() {
        return mSeekBarItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSeekBarItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        //Create a new view based on previously created xml layout file
        View v = View.inflate(mContext, R.layout.seekbar_item, null);

        //Attach views to be able to work with them
        TextView textView = v.findViewById(R.id.seekBarItem_controlName);
        SeekBar seekBar = v.findViewById(R.id.seekBarItem_seekBar);

        //Assign the values to the items
        textView.setText(mSeekBarItemList.get(i).getmSeekBarText());
       // textView.setText( ((SeekBarItem) getItem(i)).getmSeekBarText());
        seekBar.setMax(100);
        seekBar.setProgress(mSeekBarItemList.get(i).getmSeekBarProgress());


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String progress = String.valueOf(seekBar.getProgress());
                String topic = seekBarItemList.get(i).getmPublishTopic();
                System.out.println("******************" +"Execute async task");

                new CustomAsyncTask().execute(topic,progress);
            }
        });


        return v;

    }

    public void updateList(List<SeekBarItem> mSeekBarItemList) {
        this.mSeekBarItemList = mSeekBarItemList;
        notifyDataSetChanged();
    }


    private class CustomAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Double doInBackground(String... strings) {
            try {
                String topic = strings[0];
                String progress = strings[1];

                SharedPreferences settings = mContext.getSharedPreferences("settings", MODE_PRIVATE);
                MqttClient mqttClient = new MqttClient(settings.getString("etServerAddress", ""), "mobileClient", new MemoryPersistence());
                MqttConnectOptions connOptions = new MqttConnectOptions();
                connOptions.setCleanSession(false);
                //  connOptions.setKeepAliveInterval(20 * 60);
                mqttClient.connect(connOptions);
                MqttMessage message = new MqttMessage();
                message.setPayload(progress.getBytes());
                mqttClient.publish(topic,message);
                System.out.println("******************" +message.toString());
            } catch (MqttException e) {
                e.printStackTrace();
                return 0.0;
            }

            return 1.0;
        }

        @Override
        protected void onPostExecute(Double result) {
            if(result == 0.0){
                System.out.println("*******************" + result);
                Toast.makeText(mContext.getApplicationContext(),"Connection to MqttServer failed.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
