package com.contactmanagement.status;

import java.util.HashMap;

public final class ResponseStatus {
	

		private String status = "";
		private String value = "";
		private Object data = "";
		private String message="";
		HashMap<String, String> statusMap = new HashMap<>();
		HashMap<String, Object> statusDataMap = new HashMap<>();
		
		public ResponseStatus(String status, String value){
			this.status = status;			
			this.value = value;	
		}
		public ResponseStatus(String status, String value,String msg){
			this(status, value);
			this.message=msg;
		}
		public ResponseStatus(String status, Object data,String message){
			this.status = status;	
			this.message=message;
			this.data = data;				
		}
		
		public HashMap<String, String> getStatus() {			
			this.statusMap.put("status", this.status);			
			this.statusMap.put("value", this.value);
			this.statusMap.put("message", this.message);
			return statusMap;
		}
		
		public HashMap<String, Object> getResponseData() {			
			this.statusDataMap .put("status", this.status);			
			this.statusDataMap .put("data", this.data);	
			this.statusDataMap.put("message", this.message);
			return statusDataMap ;
		}
		
		

		 
	
}
