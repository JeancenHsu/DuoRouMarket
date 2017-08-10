package duorou;

public class Goods_info {
	private int goods_id;
	private int cate_id;
	protected String cate_name;
	private String goods_name;
	private String pic_link;
	private String description;
	private double single_price;
	private int goods_amount;
	private String goods_state;
	private String location;
	private String location_province;
	private String location_city;
	private String english_name;
	
	public String getLocation_province() {
		return location_province;
	}
	public void setLocation_province(String location_province) {
		this.location_province = location_province;
	}
	public String getLocation_city() {
		return location_city;
	}
	public void setLocation_city(String location_city) {
		this.location_city = location_city;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getPic_link() {
		return pic_link;
	}
	public void setPic_link(String pic_link) {
		this.pic_link = pic_link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSingle_price() {
		return single_price;
	}
	public void setSingle_price(double single_price) {
		this.single_price = single_price;
	}
	public int getGoods_amount() {//ÊýÁ¿
		return goods_amount;
	}
	public void setGoods_amount(int goods_amount) {
		this.goods_amount = goods_amount;
	}
	public String getGoods_state() {
		return goods_state;
	}
	public void setGoods_state(String goods_state) {
		this.goods_state = goods_state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
}
