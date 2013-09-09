package com.adhi.webservice;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServerBackgroundService extends Service {

	   private NotificationManager notifyManager = null;
       private WebServer server = null;
       
       @Override
       public void onCreate() {
               super.onCreate();
               
               notifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
               server = new WebServer(this, notifyManager);
       }

       @Override
       public void onDestroy() {
               server.stopThread();
               notifyManager.cancel(0);
               
               notifyManager = null;
               
               super.onDestroy();
       }

       @Override
       public int onStartCommand(Intent intent, int flags, int startId) {
               
               server.startThread();
               NotificationUtil.showNotification(notifyManager, this, "Server Started", "Listning for " +
               		"events");
               
               return START_STICKY;
       }

       @Override
       public boolean onUnbind(Intent intent) {
               return super.onUnbind(intent);
       }

       @Override
       public IBinder onBind(Intent intent) {
               return null;
       }

}
