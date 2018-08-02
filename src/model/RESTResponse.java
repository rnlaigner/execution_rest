package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTResponse 
{

	private String status;
	
	private int entriesUpdated;
	
	private int entriesAdded;
	
	private String duration;
	
	private String msg;
	
	public RESTResponse() {}
	
	public RESTResponse(String status, int entriesUpdated, int entriesAdded, String duration, String msg) {
		super();
		this.status = status;
		this.entriesUpdated = entriesUpdated;
		this.entriesAdded = entriesAdded;
		this.duration = duration;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEntriesUpdated() {
		return entriesUpdated;
	}

	public void setEntriesUpdated(int entriesUpdated) {
		this.entriesUpdated = entriesUpdated;
	}

	public int getEntriesAdded() {
		return entriesAdded;
	}

	public void setEntriesAdded(int entriesAdded) {
		this.entriesAdded = entriesAdded;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	
	
}
