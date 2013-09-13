package com.adhi.webclient.util;

public class ClientUtil {
	
	public static final String TAG = "ClientApp";
	
	private static String messagingServicePath = "/message";
	private static String dataServicePath = "/services/SimpleStockQuoteService";
	private static String esbAddress = "http://192.168.105.103";
	private static String esbPort = "8280";
	
	public static void setMessagingServicePath(String messagingServicePath) {
		ClientUtil.messagingServicePath = messagingServicePath;
	}
	public static void setEsbAddress(String esbAddress) {
		ClientUtil.esbAddress = esbAddress;
	}
	public static void setEsbPort(String esbPort) {
		ClientUtil.esbPort = esbPort;
	}
	public static String getMessagingServicePath() {
		return messagingServicePath;
	}
	public static String getEsbAddress() {
		return esbAddress;
	}
	public static String getEsbPort() {
		return esbPort;
	}
	
	public static String getDataServicePath() {
		return dataServicePath;
	}
	public static void setDataServicePath(String dataServicePath) {
		ClientUtil.dataServicePath = dataServicePath;
	}
	/**
	 * url for the messaging server deployed in the ESB
	 * this is to connect to asynchronous service
	 * @return
	 */
	public static String getMessagingServiceUrl(){
		return esbAddress + ":" + esbPort + messagingServicePath;
	}
	
	/**
	 * url for the proxy service deployed in the ESB
	 * this is to connect to synchronous service
	 * @return
	 */
	public static String getDataRequestServiceUrl(){
		return esbAddress + ":" + esbPort + dataServicePath;
	}
	

}
