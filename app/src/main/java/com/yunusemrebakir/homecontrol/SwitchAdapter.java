package com.yunusemrebakir.homecontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.yunusemrebakir.homecontrol.MainActivity.switchItemList;

public class SwitchAdapter extends BaseAdapter {
    Context mContext;
    private List<SwitchItem> mSwitchItemList;

    public SwitchAdapter(Context mContext, List<SwitchItem> mSwitchItemList) {
        this.mContext = mContext;
        this.mSwitchItemList = mSwitchItemList;
    }

    @Override
    public int getCount() {
        return mSwitchItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSwitchItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        //Create a new view based on previously created xml layout file
        final View v = View.inflate(mContext, R.layout.switch_item, null);

        //Attach views to be able to work with them
        final TextView textView = v.findViewById(R.id.switchItem_controlName);
        final SwitchButton switchButton = v.findViewById(R.id.switchItem_switch);

        //Assign the values to the items
        textView.setText(mSwitchItemList.get(i).getmSwitchText());
        switchButton.setChecked(mSwitchItemList.get(i).getmSwitchState());

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                String topic = switchItemList.get(i).getmPublishTopic();
                String valueOn = switchItemList.get(i).getmPublishValueOn();
                String valueOff = switchItemList.get(i).getmPublishValueOff();
                System.out.println("******************" +"Execute async task");

                if (isChecked){
                    new CustomAsyncTask().execute(topic, valueOn);
                }else{
                    new CustomAsyncTask().execute(topic, valueOff);
                }
            }
        });

        return v;
    }

    public void updateList(List<SwitchItem> mSwitchItemList) {
        this.mSwitchItemList = mSwitchItemList;
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
                String value = strings[1];

                SharedPreferences settings = mContext.getSharedPreferences("settings", MODE_PRIVATE);
                MqttClient mqttClient = new MqttClient(settings.getString("etServerAddress", ""), "mobileClient", new MemoryPersistence());
                MqttConnectOptions connOptions = new MqttConnectOptions();
                connOptions.setCleanSession(false);
                //  connOptions.setKeepAliveInterval(20 * 60);
                mqttClient.connect(connOptions);
                MqttMessage message = new MqttMessage();
                message.setPayload(value.getBytes());
                mqttClient.publish(topic,message);
                System.out.println("******************" +message.toString());
                mqttClient.disconnect();
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
