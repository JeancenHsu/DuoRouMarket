package duorou;

import java.sql.Timestamp;

public class Order_info {
	protected String order_id;
	protected int user_id;
	protected int user_rec_id;
	protected Timestamp start_time;
	protected Timestamp finish_time;
	protected String status;
	protected int whole_number;
	protected double whole_price;
	
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_rec_id() {
		return user_rec_id;
	}
	public void setUser_rec_id(int user_rec_id) {
		this.user_rec_id = user_rec_id;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getWhole_number() {
		return whole_number;
	}
	public void setWhole_number(int whole_number) {
		this.whole_number = whole_number;
	}
	public double getWhole_price() {
		return whole_price;
	}
	public void setWhole_price(double whole_price) {
		this.whole_price = whole_price;
	}
}
