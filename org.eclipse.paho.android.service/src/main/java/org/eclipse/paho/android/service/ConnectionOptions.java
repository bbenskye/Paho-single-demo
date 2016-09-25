package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * Created by yangpeng on 2016/9/21.
 */

public class ConnectionOptions implements Parcelable {
    private boolean cleanSession = true;
    private int timeout = 80;

    private int keepAlive = 200;
    private String userName;
    private String password;
    private String lwtTopic;
    private String lwtMessage;
    private int lwtQos = 0;
    private boolean lwtRetain =  false;
    private static String Empty = new String();
    public ConnectionOptions (){

    }
    public ConnectionOptions(MqttConnectOptions options){
        cleanSession = options.isCleanSession();
        timeout = options.getConnectionTimeout();
        keepAlive = options.getKeepAliveInterval();
        if(options.getUserName()!=null&&!options.getUserName().equals(Empty)){
            userName = options.getUserName();
        }
        if(options.getPassword()!=null&&!options.getPassword().equals(Empty)){
            password = options.getPassword().toString();
        }

    }
    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLwtTopic() {
        return lwtTopic;
    }

    public void setLwtTopic(String lwtTopic) {
        this.lwtTopic = lwtTopic;
    }

    public String getLwtMessage() {
        return lwtMessage;
    }

    public void setLwtMessage(String lwtMessage) {
        this.lwtMessage = lwtMessage;
    }

    public int getLwtQos() {
        return lwtQos;
    }

    public void setLwtQos(int lwtQos) {
        this.lwtQos = lwtQos;
    }

    public boolean isLwtRetain() {
        return lwtRetain;
    }

    public void setLwtRetain(boolean lwtRetain) {
        this.lwtRetain = lwtRetain;
    }

    protected ConnectionOptions(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<ConnectionOptions> CREATOR = new Creator<ConnectionOptions>() {
        @Override
        public ConnectionOptions createFromParcel(Parcel in) {
            return new ConnectionOptions(in);
        }

        @Override
        public ConnectionOptions[] newArray(int size) {
            return new ConnectionOptions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cleanSession?1:0);
        dest.writeInt(timeout);
        dest.writeInt(keepAlive);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(lwtTopic);
        dest.writeString(lwtMessage);
        dest.writeInt(lwtQos);
        dest.writeInt(lwtRetain?1:0);
    }
    public void readFromParcel(Parcel in){
        cleanSession = in.readInt()==1;
        timeout = in.readInt();
        keepAlive = in.readInt();
        userName = in.readString();
        password = in.readString();
        lwtTopic = in.readString();
        lwtMessage = in.readString();
        lwtQos = in.readInt();
        lwtRetain = in.readInt()==1;
    }
    public MqttConnectOptions convertToMqtt(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(timeout);
        options.setKeepAliveInterval(keepAlive);
        if(userName!=null&&!userName.equals(new String()))
        options.setUserName(userName);
        if(password!=null&&!password.equals(new String()))
        options.setPassword(password.toCharArray());
        if(lwtTopic!=null&&!lwtTopic.equals(new String()))
        options.setWill(lwtTopic,lwtMessage.getBytes(),lwtQos,lwtRetain);
        return options;
    }
}
