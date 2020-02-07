
public class StationDetails {
	private int available;
	private int free;
	private int total;
	private int updated;
	private int connected;
	
	public StationDetails () {}
	
	
	
	public StationDetails(int available, int free, int total, int updated, int connected) {
		super();
		this.available = available;
		this.free = free;
		this.total = total;
		this.updated = updated;
		this.connected = connected;
	}



	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUpdated() {
		return updated;
	}
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	public int getConnected() {
		return connected;
	}
	public void setConnected(int connected) {
		this.connected = connected;
	}
	@Override
	public String toString() {
		return "StationDetails [available=" + available + ", free=" + free + ", total=" + total + ", updated=" + updated
				+ ", connected=" + connected + "]";
	}
	
	
}
