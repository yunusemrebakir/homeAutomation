package com.yunusemrebakir.homecontrol;

public class SeekBarItem {
    private String mSeekBarText;
    private int mSeekBarProgress;
    private String mPublishAddress;

    public SeekBarItem(String mSeekBarText, int mSeekBarProgress, String mPublishAddress) {
        this.mSeekBarText = mSeekBarText;
        this.mSeekBarProgress = mSeekBarProgress;
        this.mPublishAddress = mPublishAddress;
    }

    public String getmSeekBarText() {
        return mSeekBarText;
    }

    public void setmSeekBarText(String mSeekBarText) {
        this.mSeekBarText = mSeekBarText;
    }

    public int getmSeekBarProgress() {
        return mSeekBarProgress;
    }

    public void setmSeekBarProgress(int mSeekBarProgress) {
        this.mSeekBarProgress = mSeekBarProgress;
    }

    public String getmPublishTopic() {
        return mPublishAddress;
    }

    public void setmPublishAddress(String mPublishAddress) {
        this.mPublishAddress = mPublishAddress;
    }
}
