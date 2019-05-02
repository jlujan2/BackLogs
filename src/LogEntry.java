import java.util.Date;

public class LogEntry {

	private String ipAddress;
	private Date accessTime;
	private String request;
	private Integer statusCode;
	private Integer bytesReturned;
	
	public LogEntry(String ipAddress, Date accessTime, String request, Integer statusCode, Integer bytesReturned) {
		super();
		this.ipAddress = ipAddress;
		this.accessTime = accessTime;
		this.request = request;
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getBytesReturned() {
		return bytesReturned;
	}

	public void setBytesReturned(Integer bytesReturned) {
		this.bytesReturned = bytesReturned;
	}

	
	public String printLog() {
		return "LogEntry [ipAddress=" + getIpAddress() + ", accessTime=" + getAccessTime() + ", request=" + getRequest()
				+ ", statusCode=" + getStatusCode() + ", bytesReturned=" + getBytesReturned() + "]";
	}
	
}

