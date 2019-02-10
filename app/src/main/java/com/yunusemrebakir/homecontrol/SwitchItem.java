package com.yunusemrebakir.homecontrol;

public class SwitchItem {
    private String mSwitchText;
    private String mPublishValueOn;
    private String mPublishValueOff;
    private String mPublishTopic;
    private boolean mSwitchState;

    public SwitchItem(String mSwitchText, boolean mSwitchState, String mPublishTopic, String mPublishValueOn, String mPublishValueOff) {
        this.mSwitchText = mSwitchText;
        this.mSwitchState = mSwitchState;
        this.mPublishValueOn = mPublishValueOn;
        this.mPublishValueOff = mPublishValueOff;
        this.mPublishTopic = mPublishTopic;
    }

    public String getmSwitchText() {
        return mSwitchText;
    }

    public void setmSwitchText(String mSwitchText) {
        this.mSwitchText = mSwitchText;
    }

    public boolean getmSwitchState() {
        return mSwitchState;
    }

    public void setmSwitchState(boolean mSwitchState) {
        this.mSwitchState = mSwitchState;
    }

    public String getmPublishValueOn() {
        return mPublishValueOn;
    }

    public void setmPublishValueOn(String mPublishValueOn) {
        this.mPublishValueOn = mPublishValueOn;
    }

    public String getmPublishValueOff() {
        return mPublishValueOff;
    }

    public void setmPublishValueOff(String mPublishValueOff) {
        this.mPublishValueOff = mPublishValueOff;
    }

    public String getmPublishTopic() {
        return mPublishTopic;
    }

    public void setmPublishTopic(String mPublishTopic) {
        this.mPublishTopic = mPublishTopic;
    }
}
