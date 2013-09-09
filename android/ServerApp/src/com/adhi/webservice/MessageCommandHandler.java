package com.adhi.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.util.EntityUtils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

public class MessageCommandHandler implements HttpRequestHandler{
	private static final long[] VIBRATE = {0,100,200,200,100,300};
	
	private Context context = null;
	private NotificationManager notifyManager = null;
	
	public MessageCommandHandler(Context context, NotificationManager notifyManager){
		this.context = context;
		this.notifyManager = notifyManager;
	}
	
	@Override
	public void handle(HttpRequest request, HttpResponse response, HttpContext httpContext) throws HttpException, IOException {
		String uriString = request.getRequestLine().getUri();
		Uri uri = Uri.parse(uriString);
		//String message = URLDecoder.decode(uri.getQueryParameter("msg"));
		
		
		String body = "";
		
		 HttpEntity reqEntity = null;
	        if (request instanceof HttpEntityEnclosingRequest)
	        	reqEntity = ((HttpEntityEnclosingRequest)request).getEntity();

	        // For some reason, just putting the incoming entity into
	        // the response will not work. We have to buffer the message.
	        byte[] data;
	        if (reqEntity == null) {
	            data = new byte [0];
	        } else {
	            data = EntityUtils.toByteArray(reqEntity);
	        }
	        body = new String(data);
	       
		Log.i("WebService", body);
		//AppLog.logString("Message URI: " + uriString);
		Log.i("WebService", "Message URI: " + uriString);
		//displayMessage(message);
		//NotificationUtil.showNotification(notifyManager, context, "Notification", "Message URI: " + uriString);
		String message = body;//XMLProcessor.getValue(body, "message");
		String title = "notification";//XMLProcessor.getValue(body, "title");
		NotificationUtil.showNotification(notifyManager, context, title, message);
		HttpEntity entity = new EntityTemplate(new ContentProducer() {
    		public void writeTo(final OutputStream outstream) throws IOException {
    			OutputStreamWriter writer = new OutputStreamWriter(outstream, "UTF-8");
    			String resp = createRespMsg();//Utility.openHTMLString(context, R.raw.messagesend);
            
    			writer.write(resp);
    			writer.flush();
    		}
    	});
		
		response.setHeader("Content-Type", "text/html");
		response.setEntity(entity);
	}
	
	protected void displayMessage(final String message) {
		/*
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		String text = context.getString(R.string.message_ticker_text);
		Notification notification = new Notification(R.drawable.messageicon, text, System.currentTimeMillis());
		Intent startIntent = new Intent(context,AWSMessageActivity.class);
		boolean isVibrate = pref.getBoolean(Constants.PREF_VIBRATE, true);
		boolean isPlaysound = pref.getBoolean(Constants.PREF_PLAYSOUND, true);
		String notificationSound = pref.getString(Constants.PREF_RINGTONE, "");
		
		startIntent.putExtra(Constants.AWS_MESSAGE, message);
		
		PendingIntent intent = PendingIntent.getActivity(context, 0, startIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.flags |= (Notification.FLAG_ONLY_ALERT_ONCE | Notification.FLAG_AUTO_CANCEL);
		
		if(isVibrate)
			notification.vibrate = VIBRATE;
		
		if(isPlaysound && notificationSound.length() > 0)
			notification.sound = Uri.parse(notificationSound);
		
		notification.setLatestEventInfo(context, 
				context.getString(R.string.message_title), 
				message, 
				intent);
		
		notifyManager.notify(R.string.message_title, notification);
			*/
	} 
	private String createRespMsg(){
		return "<html><body><p>Message from Android server</p></body></html>";
	}
}