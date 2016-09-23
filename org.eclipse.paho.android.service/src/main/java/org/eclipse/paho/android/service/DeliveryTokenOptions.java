package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangpeng on 2016/9/23.
 */

public class DeliveryTokenOptions implements Parcelable {
    int messageId ;
    boolean SessionPresent ;
    int GrantedQos[];

    protected DeliveryTokenOptions(Parcel in) {
        messageId = in.readInt();
        SessionPresent = in.readByte() != 0;
        GrantedQos = in.createIntArray();
    }
    public DeliveryTokenOptions(){

    }
    public static final Creator<DeliveryTokenOptions> CREATOR = new Creator<DeliveryTokenOptions>() {
        @Override
        public DeliveryTokenOptions createFromParcel(Parcel in) {
            return new DeliveryTokenOptions(in);
        }

        @Override
        public DeliveryTokenOptions[] newArray(int size) {
            return new DeliveryTokenOptions[size];
        }
    };

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public boolean isSessionPresent() {
        return SessionPresent;
    }

    public void setSessionPresent(boolean sessionPresent) {
        SessionPresent = sessionPresent;
    }

    public int[] getGrantedQos() {
        return GrantedQos;
    }

    public void setGrantedQos(int[] grantedQos) {
        GrantedQos = grantedQos;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(messageId);
        dest.writeByte((byte) (SessionPresent ? 1 : 0));
        dest.writeIntArray(GrantedQos);
    }
}
