package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by yangpeng on 2016/9/23.
 */

public class AidlMessage implements Parcelable{
    private byte[] payload;
    private int qos = 1;
    private boolean retained = false;
    private int messageId;

    protected AidlMessage(Parcel in) {
        payload = in.createByteArray();
        qos = in.readInt();
        retained = in.readByte() != 0;
        messageId = in.readInt();
    }
    public AidlMessage(){

    }
    public AidlMessage(MqttMessage message){
        payload = message.getPayload();
        qos = message.getQos();
        retained = message.isRetained();
        messageId = message.getId();

    }
    public static final Creator<AidlMessage> CREATOR = new Creator<AidlMessage>() {
        @Override
        public AidlMessage createFromParcel(Parcel in) {
            return new AidlMessage(in);
        }

        @Override
        public AidlMessage[] newArray(int size) {
            return new AidlMessage[size];
        }
    };

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public boolean isRetained() {
        return retained;
    }

    public void setRetained(boolean retained) {
        this.retained = retained;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(payload);
        dest.writeInt(qos);
        dest.writeByte((byte) (retained ? 1 : 0));
        dest.writeInt(messageId);
    }
    public MqttMessage convertMqttMessage(){
        MqttMessage message = new MqttMessage();
        message.setId(messageId);
        message.setPayload(payload);
        message.setQos(qos);
        message.setRetained(retained);
        return message;
    }

}
