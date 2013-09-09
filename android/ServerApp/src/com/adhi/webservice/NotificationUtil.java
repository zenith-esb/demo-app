package com.adhi.webservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationUtil {

	private static final int NOTIFICATION_ID = 0;
	
	public static void showNotification(NotificationManager notificationManager , Context context,
			String title, String message){
		//String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = notificationManager;

		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Notification";
		long when = System.currentTimeMillis();
				
		CharSequence contentTitle = title;
		CharSequence contentText = message;

		// when the notification is clicked, following Activity starts
		Intent notificationIntent = new Intent(context,
				MessageDisplayActivity.class);
		notificationIntent.putExtra("title", title);
		notificationIntent.putExtra("message", message);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

		Notification notification =  new Notification(icon, tickerText, when);
		
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		mNotificationManager.notify(NOTIFICATION_ID, notification);
	}
}
