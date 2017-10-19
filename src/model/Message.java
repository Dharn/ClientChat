package model;

import java.util.Date;

public class Message {
	int id;
	int userId;
	String message;
	Date dateMessage;
	int salonId;
	
	public Message(){
		
	}
	
	public Message(int userId, String message, int salonId){
		this.userId = userId;
		this.message = message;
		this.salonId = salonId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public int getSalonId() {
		return salonId;
	}

	public void setSalonId(int salonId) {
		this.salonId = salonId;
	}
	
	

}
