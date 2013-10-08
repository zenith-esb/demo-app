package com.adhi.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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


public class MessageCommandHandler implements HttpRequestHandler{
	
	
	
	@Override
	public void handle(HttpRequest request, HttpResponse response, HttpContext httpContext) throws HttpException, IOException {
		String uriString = request.getRequestLine().getUri();
		//Uri uri = Uri.parse(uriString);
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
	       
		
		//AppLog.logString("Message URI: " + uriString);
		System.out.println( "Message URI: " + uriString);
		//displayMessage(message);
		//NotificationUtil.showNotification(notifyManager, context, "Notification", "Message URI: " + uriString);
		String[] content = getContent(body);
		String message = null;
		if(content.length == 2)
			message = content[1];//XMLProcessor.getValue(body, "message");
		String title = content[0];//XMLProcessor.getValue(body, "title");
		
		System.out.println("Message : " + message);
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
	
	
	private String createRespMsg(){
		return "<html><body><p>Message from Java Http server</p></body></html>";
	}
	
	private String[] getContent(String body){
		String[] content;	
		
		content = body.split(","); //message body is in text/csv

		return content;
		
	}
}