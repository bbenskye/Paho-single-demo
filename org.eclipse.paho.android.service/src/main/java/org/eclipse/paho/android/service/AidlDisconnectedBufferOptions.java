package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

import java.lang.reflect.ParameterizedType;

/**
 * Created by yangpeng on 2016/9/23.
 */
public class AidlDisconnectedBufferOptions  implements Parcelable{
    /**
     * The default size of the disconnected buffer
     */
    public static final int DISCONNECTED_BUFFER_SIZE_DEFAULT = 5000;

    public static final boolean DISCONNECTED_BUFFER_ENABLED_DEFAULT = false;

    public static final boolean PERSIST_DISCONNECTED_BUFFER_DEFAULT = false;

    public static final boolean DELETE_OLDEST_MESSAGES_DEFAULT = false;

    private int bufferSize = DISCONNECTED_BUFFER_SIZE_DEFAULT;
    private boolean bufferEnabled = DISCONNECTED_BUFFER_ENABLED_DEFAULT;
    private boolean persistBuffer = PERSIST_DISCONNECTED_BUFFER_DEFAULT;
    private boolean deleteOldestMessages = DELETE_OLDEST_MESSAGES_DEFAULT;

    protected AidlDisconnectedBufferOptions(Parcel in) {
        bufferSize = in.readInt();
        bufferEnabled = in.readByte() != 0;
        persistBuffer = in.readByte() != 0;
        deleteOldestMessages = in.readByte() != 0;
    }
    public AidlDisconnectedBufferOptions(DisconnectedBufferOptions options){
        bufferEnabled = options.isBufferEnabled();
        bufferSize = options.getBufferSize();
        persistBuffer = options.isPersistBuffer();
        deleteOldestMessages  = options.isDeleteOldestMessages();
    }
    public static final Creator<AidlDisconnectedBufferOptions> CREATOR = new Creator<AidlDisconnectedBufferOptions>() {
        @Override
        public AidlDisconnectedBufferOptions createFromParcel(Parcel in) {
            return new AidlDisconnectedBufferOptions(in);
        }

        @Override
        public AidlDisconnectedBufferOptions[] newArray(int size) {
            return new AidlDisconnectedBufferOptions[size];
        }
    };

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public boolean isBufferEnabled() {
        return bufferEnabled;
    }

    public void setBufferEnabled(boolean bufferEnabled) {
        this.bufferEnabled = bufferEnabled;
    }

    public boolean isPersistBuffer() {
        return persistBuffer;
    }

    public void setPersistBuffer(boolean persistBuffer) {
        this.persistBuffer = persistBuffer;
    }

    public boolean isDeleteOldestMessages() {
        return deleteOldestMessages;
    }

    public void setDeleteOldestMessages(boolean deleteOldestMessages) {
        this.deleteOldestMessages = deleteOldestMessages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bufferSize);
        dest.writeByte((byte) (bufferEnabled ? 1 : 0));
        dest.writeByte((byte) (persistBuffer ? 1 : 0));
        dest.writeByte((byte) (deleteOldestMessages ? 1 : 0));
    }
    public DisconnectedBufferOptions convertOptions (){
        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
        disconnectedBufferOptions.setBufferEnabled(bufferEnabled);
        disconnectedBufferOptions.setBufferSize(bufferSize);
        disconnectedBufferOptions.setDeleteOldestMessages(deleteOldestMessages);
        disconnectedBufferOptions.setPersistBuffer(persistBuffer);
        return disconnectedBufferOptions;
    }
}
