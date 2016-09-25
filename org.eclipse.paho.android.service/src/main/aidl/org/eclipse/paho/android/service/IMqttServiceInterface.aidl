// IMqttServiceInterface.aidl
package org.eclipse.paho.android.service;
import org.eclipse.paho.android.service.IAidlPersistence;
import org.eclipse.paho.android.service.ConnectionOptions;
import org.eclipse.paho.android.service.DeliveryTokenOptions;
import org.eclipse.paho.android.service.AidlMessage;
import org.eclipse.paho.android.service.AidlDisconnectedBufferOptions;
// Declare any non-default types here with import statements

interface IMqttServiceInterface {

 String getClient(in String serverURI,in String clientId,
//暂时不处理persitence参数
 in String contextId,in IAidlPersistence  persistence);
 void connect(in String clientHandle,in ConnectionOptions connectOptions,
      in String invocationContext, String activityToken);
 void setTraceEnabled(in boolean traceEnabled);
 void setTraceCallbackId(in String traceCallbackId);
 void reconnect();
 void close(in String clientHandle);
 void disconnect(in String clientHandle,in String invocationContext,
       in String activityToken);
 void disconnectContainTimeout(in String clientHandle, in long quiesceTimeout,
            in String invocationContext,in String activityToken);
  DeliveryTokenOptions publish(String clientHandle, String topic,
       in AidlMessage message, String invocationContext, String activityToken);
   DeliveryTokenOptions publishMessage(String clientHandle, String topic,
     in byte[] payload, int qos, boolean retained,
      String invocationContext, String activityToken);
  void subscribe(String clientHandle, String topic, int qos,
      String invocationContext, String activityToken);
 void subscribeArray(String clientHandle, in String[] topic, in int[] qos,
      String invocationContext, String activityToken);
 void unsubscribe(String clientHandle,  String topic,
       String invocationContext, String activityToken);
       void unsubscribeArray(String clientHandle, in String[] topic,
             String invocationContext, String activityToken);
  int acknowledgeMessageArrival(String clientHandle, String id);
  boolean isOnline();
  void notifyClientsOffline();
  boolean isConnected(String clientHandle);
  void traceError(String tag, String message);
  int getBufferedMessageCount(String clientHandle);
  AidlMessage getBufferedMessage(String clientHandle, int bufferIndex);
  void deleteBufferedMessage(String clientHandle, int bufferIndex);
void setBufferOpts(String clientHandle, in AidlDisconnectedBufferOptions bufferOpts);
}
